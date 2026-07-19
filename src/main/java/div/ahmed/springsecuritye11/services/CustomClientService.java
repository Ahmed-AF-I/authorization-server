package div.ahmed.springsecuritye11.services;

import div.ahmed.springsecuritye11.entities.Client;
import div.ahmed.springsecuritye11.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(Client.form(registeredClient));
    }

    @Nullable
    @Override
    public RegisteredClient findById(String id) {
        var client = clientRepository.findById(Long.parseLong(id))
                .orElseThrow();
        return Client.from(client);
    }

    @Nullable
    @Override
    public RegisteredClient findByClientId(String clientId) {
        var client = clientRepository.findByClientId(clientId)
                .orElseThrow();
        return Client.from(client);
    }
}
