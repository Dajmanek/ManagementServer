package pl.krakow.up.managementserver.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.krakow.up.managementserver.persistence.model.Client;

public interface ClientDao extends JpaRepository<Client, Long> {
}
