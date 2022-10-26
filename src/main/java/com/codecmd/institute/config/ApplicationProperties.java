package com.codecmd.institute.config;

import com.codecmd.institute.shared.ApplicationDefault;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Properties specific to Institute.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    @Getter
    private final ApiDocs apiDocs = new ApiDocs();

    @Getter
    private final Security security = new Security();

    @Getter
    private final CorsConfiguration cors = new CorsConfiguration();

    @Getter
    public static class Security {

        @Setter
        private String contentSecurityPolicy = ApplicationDefault.Security.contentSecurityPolicy;

        private final ClientAuthorization clientAuthorization = new ClientAuthorization();

        private final Authentication authentication = new Authentication();

        private final RememberMe rememberMe = new RememberMe();

        private final OAuth2 oauth2 = new OAuth2();

        @Getter
        @Setter
        public static class ClientAuthorization {

            private String accessTokenUri = ApplicationDefault.Security.ClientAuthorization.accessTokenUri;

            private String tokenServiceId = ApplicationDefault.Security.ClientAuthorization.tokenServiceId;

            private String clientId = ApplicationDefault.Security.ClientAuthorization.clientId;

            private String clientSecret = ApplicationDefault.Security.ClientAuthorization.clientSecret;
        }

        public static class Authentication {

            @Getter
            private final Jwt jwt = new Jwt();

            @Getter
            @Setter
            public static class Jwt {

                private String secret = ApplicationDefault.Security.Authentication.Jwt.secret;

                private String base64Secret = ApplicationDefault.Security.Authentication.Jwt.base64Secret;

                private long tokenValidityInSeconds = ApplicationDefault.Security.Authentication.Jwt
                        .tokenValidityInSeconds;

                private long tokenValidityInSecondsForRememberMe = ApplicationDefault.Security.Authentication.Jwt
                        .tokenValidityInSecondsForRememberMe;
            }
        }

        @Getter
        @Setter
        public static class RememberMe {

            @NotNull
            private String key = ApplicationDefault.Security.RememberMe.key;
        }

        @Getter
        @Setter
        public static class OAuth2 {
            private List<String> audience = new ArrayList<>();
        }
    }

    @Getter
    @Setter
    public static class ApiDocs {

        private String title = ApplicationDefault.ApiDocs.title;

        private String description = ApplicationDefault.ApiDocs.description;

        private String version = ApplicationDefault.ApiDocs.version;

        private String termsOfServiceUrl = ApplicationDefault.ApiDocs.termsOfServiceUrl;

        private String contactName = ApplicationDefault.ApiDocs.contactName;

        private String contactUrl = ApplicationDefault.ApiDocs.contactUrl;

        private String contactEmail = ApplicationDefault.ApiDocs.contactEmail;

        private String license = ApplicationDefault.ApiDocs.license;

        private String licenseUrl = ApplicationDefault.ApiDocs.licenseUrl;

        private String defaultIncludePattern = ApplicationDefault.ApiDocs.defaultIncludePattern;

        private String managementIncludePattern = ApplicationDefault.ApiDocs.managementIncludePattern;

        private Server[] servers = {};

        @Getter
        @Setter
        public static class Server {

            private String url;

            private String description;
        }
    }
}
