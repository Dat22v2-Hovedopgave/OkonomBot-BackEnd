package com.example.okonombotbackend.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.example.okonombotbackend.security.error.CustomOAuth2AccessDeniedHandler;
import com.example.okonombotbackend.security.error.CustomOAuth2AuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)

@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    System.out.println("CALLED"); // Print statement for debugging
    return new BCryptPasswordEncoder(); // Return BCryptPasswordEncoder bean for password encoding
  }

  // Use this to fine tune the CORS headers, if needed (Not required for this semester)
//@Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // Create CORS configuration source
    CorsConfiguration config = new CorsConfiguration(); // Create CORS configuration
    config.setAllowCredentials(true); // Allow credentials (cookies, authorization headers, etc.)
    config.addAllowedOriginPattern("*"); // Allow all origins (domains allowed to access the resource)
    config.addAllowedHeader("*"); // Allow all headers (HTTP headers allowed in the request)
    config.addAllowedMethod("*"); // Allow all methods (HTTP methods like GET, POST, etc.)
    source.registerCorsConfiguration("/**", config); // Register CORS configuration for all paths
    return new CorsFilter(source); // Return CORS filter bean
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // This line is added to make the h2-console work (if needed)
    http.headers().frameOptions().disable(); // Disable frame options to allow h2-console

    http
            .cors().and().csrf().disable() // Disable CORS and CSRF protection
            .httpBasic(Customizer.withDefaults()) // Enable HTTP basic authentication
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Set session creation policy to stateless
            // REF: https://mflash.dev/post/2021/01/19/error-handling-for-spring-security-resource-server/
            .exceptionHandling((exceptions) -> exceptions
                    .authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint()) // Set custom authentication entry point
                    .accessDeniedHandler(new CustomOAuth2AccessDeniedHandler()) // Set custom access denied handler
            )
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(authenticationConverter()); // Set JWT authentication converter

    http.authorizeHttpRequests((authorize) -> authorize
            // Obviously we need to be able to login without being logged in :-)
            .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll() // Allow login without authentication

            // Required in order to use the h2-console
            .requestMatchers("/h2*/**").permitAll() // Allow access to h2-console

            .requestMatchers("/").permitAll() // Allow the default index.html file

            // Next two lines only required if you plan to do the cookie/session-demo from within this project
            .requestMatchers("/session-demo.html").permitAll() // Allow access to session-demo.html
            .requestMatchers("/api/cookie/**").permitAll() // Allow access to cookie API

            // Allow anonymous access to this endpoint
            //.requestMatchers(HttpMethod.GET,"/api/demo/anonymous").permitAll()

            // necessary to allow for "nice" JSON Errors
            .requestMatchers("/error").permitAll() // Allow access to error endpoint

            .requestMatchers("/", "/**").permitAll() // Allow access to all other endpoints

            //.requestMatchers(HttpMethod.GET,"/api/demo/anonymous").permitAll()

            // Demonstrates another way to add roles to an endpoint
            // .requestMatchers(HttpMethod.GET, "/api/demo/admin").hasAuthority("ADMIN")
            .anyRequest().authenticated()); // Require authentication for all other requests

    return http.build(); // Build and return the SecurityFilterChain
  }

  @Bean
  public JwtAuthenticationConverter authenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles"); // Set authorities claim name to "roles" (claims in the JWT that represent roles)
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix(""); // Remove authority prefix
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter); // Set JWT granted authorities converter
    return jwtAuthenticationConverter; // Return JWT authentication converter bean
  }

  /* Initialize static value "secret" */
  @Value("${app.secret-key}")
  private String secretKey;
  public static String tokenSecret;

  @Value("${app.secret-key}")
  public void setStaticValue(String secretKey) {
    SecurityConfig.tokenSecret = secretKey; // Set static token secret
  }
  /* End of Initialize static value "secret" */

  @Bean
  public JwtEncoder jwtEncoder() throws JOSEException {
    System.out.println("1) ---> " + tokenSecret); // Print statement for debugging
    SecretKey key = new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256"); // Create secret key from token secret
    JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<>(key); // Create JWK source from secret key
    return new NimbusJwtEncoder(immutableSecret); // Return Nimbus JWT encoder bean
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    System.out.println("2) ---> " + tokenSecret); // Print statement for debugging
    SecretKey originalKey = new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256"); // Create original secret key from token secret
    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(originalKey).build(); // Build Nimbus JWT decoder
    return jwtDecoder; // Return Nimbus JWT decoder bean
  }

  // TBD --> IS THIS THE RIGHT WAY
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
          throws Exception {
    return authenticationConfiguration.getAuthenticationManager(); // Return authentication manager from configuration
  }
}
