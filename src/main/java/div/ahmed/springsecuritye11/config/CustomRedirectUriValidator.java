package div.ahmed.springsecuritye11.config;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationException;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.function.Consumer;

public class CustomRedirectUriValidator implements
        Consumer<OAuth2AuthorizationCodeRequestAuthenticationContext> {

    @Override
    public void accept(
            OAuth2AuthorizationCodeRequestAuthenticationContext context
    ) {
        OAuth2AuthorizationCodeRequestAuthenticationToken authentication =
                context.getAuthentication();
        RegisteredClient registeredClient = context.getRegisteredClient();
        String redirectUri = authentication.getRedirectUri();

        if (redirectUri != null && !redirectUri.startsWith("https://")
                && !redirectUri.startsWith("http://127.0.0.1")
                && !redirectUri.startsWith("http://localhost")) {
            throw new OAuth2AuthorizationCodeRequestAuthenticationException(
                    new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST), null);
        }
    }
}
