package com.yourcompany.webservice.config;

public class ConfigConstants {

    public static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // authentication endpoints
            "/v1/auth/**",
            // oauth2 endpoints
            "/oauth/authorize",
            "/oauth/token"
    };
}
