package pl.krakow.up.managementserver.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.krakow.up.managementserver.persistence.model.User;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByLogin(final String login);

}
