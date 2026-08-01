package div.ahmed.springsecuritye11.services;

import div.ahmed.springsecuritye11.mapper.ClientMapper;
import div.ahmed.springsecuritye11.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomRegisteredClientRepository
        implements RegisteredClientRepository {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(clientMapper.toEntity(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        return clientRepository.findById(Long.parseLong(id))
                .map(clientMapper::toRegisteredClient)
                .orElse(null);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return clientRepository.findByClientId(clientId)
                .map(clientMapper::toRegisteredClient)
                .orElse(null);
    }
}