package div.ahmed.springsecuritye11.repositories;

import div.ahmed.springsecuritye11.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.clientId = :clientId")
    Optional<Client> findByClientId(String clientId);
}
