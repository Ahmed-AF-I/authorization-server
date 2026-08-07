package div.ahmed.springsecuritye11.mapper;

import div.ahmed.springsecuritye11.entities.Client;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ClientMapper {

    public Client toEntity(RegisteredClient registeredClient) {

        Client client = new Client();

        client.setClientId(registeredClient.getClientId());
        client.setClientSecret(registeredClient.getClientSecret());

        registeredClient.getRedirectUris()
                .stream()
                .findFirst()
                .ifPresent(client::setRedirectUri);

        registeredClient.getScopes()
                .stream()
                .findFirst()
                .ifPresent(client::setScope);

        registeredClient.getClientAuthenticationMethods()
                .stream()
                .findFirst()
                .ifPresent(method -> client.setAuthMethod(method.getValue()));

        registeredClient.getAuthorizationGrantTypes()
                .stream()
                .findFirst()
                .ifPresent(grantType -> client.setGrantTypes(grantType.getValue()));

        return client;
    }

    public RegisteredClient toRegisteredClient(Client client) {

        boolean isPublicClient = "none".equalsIgnoreCase(client.getAuthMethod());

        var builder = RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .redirectUri(client.getRedirectUri())
                .scope(client.getScope())
                .clientAuthenticationMethod(
                        new ClientAuthenticationMethod(client.getAuthMethod())
                )
                .authorizationGrantType(
                        new AuthorizationGrantType(client.getGrantTypes())
                )
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(15))
                        .build()
                )
                .clientSettings(ClientSettings.builder()
                        .requireProofKey(isPublicClient)
                        .build()
                );
        if (client.getClientSecret() != null &&  !client.getClientSecret().isBlank()) {
            builder.clientSecret(client.getClientSecret());
        }
        return builder.build();
    }
}
