package com.example.okonombotbackend.security.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CustomOAuth2AccessDeniedHandler implements AccessDeniedHandler {
  public static final Logger logger = LoggerFactory.getLogger(CustomOAuth2AccessDeniedHandler.class);
  private String realmName;
  /**
   * Handles access denied exceptions for OAuth2 authenticated requests.
   * This class implements the AccessDeniedHandler interface and is responsible for
   * creating a custom error response when access is denied due to insufficient privileges.
   */
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
          throws IOException {
    logger.error(e.getLocalizedMessage(), e); // Log the error message

    Map<String, String> parameters = new LinkedHashMap<>();
    String errorMessage = e.getLocalizedMessage();
    if (Objects.nonNull(realmName)) {
      parameters.put("realm", realmName); // Add realm name to parameters if it's not null
    }
    if (request.getUserPrincipal() instanceof AbstractOAuth2TokenAuthenticationToken) {
      errorMessage = "The request requires privileges other than those provided by the access token.";
      parameters.put("error", "insufficient_scope"); // Add error type
      parameters.put("error_description", errorMessage); // Add error description
      parameters.put("error_uri", "https://tools.ietf.org/html/rfc6750#section-3.1"); // Add error URI for more details
    }
    Map<String, String> errorResponse = new HashMap<>();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
    errorResponse.put("timestamp", df.format(new Date())); // Add timestamp
    errorResponse.put("status", "" + HttpStatus.FORBIDDEN.toString()); // Add HTTP status
    errorResponse.put("error", e.getLocalizedMessage()); // Add localized error message
    errorResponse.put("message", errorMessage); // Add error message
    errorResponse.put("path", request.getRequestURI()); // Add request URI

    //Refactor
    String wwwAuthenticate = CustomOAuth2AuthenticationEntryPoint.computeWWWAuthenticateHeaderValue(parameters); // Compute the WWW-Authenticate header value
    response.addHeader("WWW-Authenticate", wwwAuthenticate); // Add WWW-Authenticate header
    response.setStatus(HttpStatus.FORBIDDEN.value()); // Set HTTP status to 403 Forbidden
    response.setContentType(MediaType.APPLICATION_JSON_VALUE); // Set content type to JSON
    ObjectMapper mapper = new ObjectMapper();

    response.getWriter().write(mapper.writeValueAsString(errorResponse)); // Write error response as JSON
  }

  public void setRealmName(String realmName) {
    this.realmName = realmName; // Set realm name
  }
}
