package com.codecmd.institute.shared;


public interface ApplicationDefault {

    interface Security {
        String contentSecurityPolicy = "default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:";

        interface ClientAuthorization {

            String accessTokenUri = null;
            String tokenServiceId = null;
            String clientId = null;
            String clientSecret = null;
        }

        interface Authentication {

            interface Jwt {

                String secret = null;
                String base64Secret = null;
                long tokenValidityInSeconds = 1800; // 30 minutes
                long tokenValidityInSecondsForRememberMe = 2592000; // 30 days
            }
        }

        interface RememberMe {

            String key = null;
        }
    }

    interface ApiDocs {

        String title = "Application API";
        String description = "API documentation";
        String version = "0.0.1";
        String termsOfServiceUrl = null;
        String contactName = null;
        String contactUrl = null;
        String contactEmail = null;
        String license = null;
        String licenseUrl = null;
        String defaultIncludePattern = "/api/**";
        String managementIncludePattern = "/management/**";
        String host = null;
        String[] protocols = {};
        boolean useDefaultResponseMessages = true;
    }
}
