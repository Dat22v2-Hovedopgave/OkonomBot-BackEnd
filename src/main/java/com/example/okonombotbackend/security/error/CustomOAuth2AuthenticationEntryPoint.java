package com.example.okonombotbackend.security.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class handles the authentication entry point for OAuth2 authentication.
 * It implements the AuthenticationEntryPoint interface and provides a custom
 * response when authentication fails due to insufficient or invalid credentials.
 * The response includes details such as error code, description, URI, timestamp,
 * and request path, and sets the WWW-Authenticate header with appropriate parameters.
 */
public class CustomOAuth2AuthenticationEntryPoint implements AuthenticationEntryPoint {
  private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2AuthenticationEntryPoint.class);
  private String realmName;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
          throws IOException {
    logger.error(e.getLocalizedMessage(), e); // Log the error message
    //logger.debug(e.getLocalizedMessage(), e);

    HttpStatus status = HttpStatus.UNAUTHORIZED; // Set default status to UNAUTHORIZED
    String errorMessage = "Insufficient authentication details"; // Default error message
    Map<String, String> parameters = new LinkedHashMap<>();

    if (Objects.nonNull(realmName)) {
      parameters.put("realm", realmName); // Add realm name to parameters if it's not null
    }

    if (e instanceof OAuth2AuthenticationException) {
      OAuth2Error error = ((OAuth2AuthenticationException) e).getError();
      parameters.put("error", error.getErrorCode()); // Add error code to parameters
      if (StringUtils.hasText(error.getDescription())) {
        errorMessage = error.getDescription(); // Override error message with detailed description
        parameters.put("error_description", errorMessage); // Add error description to parameters
      }
      if (StringUtils.hasText(error.getUri())) {
        parameters.put("error_uri", error.getUri()); // Add error URI to parameters
      }
      if (error instanceof BearerTokenError) {
        BearerTokenError bearerTokenError = (BearerTokenError) error;
        if (StringUtils.hasText(bearerTokenError.getScope())) {
          parameters.put("scope", bearerTokenError.getScope()); // Add scope to parameters
        }
        status = ((BearerTokenError) error).getHttpStatus(); // Override status with specific HTTP status
      }
    }

    Map<String, String> errorResponse = new HashMap<>();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
    errorResponse.put("timestamp", df.format(new Date())); // Add timestamp
    errorResponse.put("status", "" + status); // Add HTTP status
    errorResponse.put("error", e.getLocalizedMessage()); // Add localized error message
    errorResponse.put("message", errorMessage); // Add error message
    errorResponse.put("path", request.getRequestURI()); // Add request URI

    String wwwAuthenticate = computeWWWAuthenticateHeaderValue(parameters); // Compute the WWW-Authenticate header value
    response.addHeader("WWW-Authenticate", wwwAuthenticate); // Add WWW-Authenticate header
    response.setStatus(status.value()); // Set response status
    response.setContentType(MediaType.APPLICATION_JSON_VALUE); // Set response content type to JSON
    ObjectMapper mapper = new ObjectMapper();

    response.getWriter().write(mapper.writeValueAsString(errorResponse)); // Write error response as JSON
  }

  public static String computeWWWAuthenticateHeaderValue(Map<String, String> parameters) {
    StringJoiner wwwAuthenticate = new StringJoiner(", ", "Bearer ", "");
    if (!parameters.isEmpty()) {
      parameters.forEach((k, v) -> wwwAuthenticate.add(k + "=\"" + v + "\"")); // Format parameters for WWW-Authenticate header
    }
    return wwwAuthenticate.toString(); // Return the formatted WWW-Authenticate header value
  }
}

