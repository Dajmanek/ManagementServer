package pl.krakow.up.managementserver.service;

import org.springframework.stereotype.Service;
import pl.krakow.up.managementserver.exception.NotFoundException;
import pl.krakow.up.managementserver.exception.UnauthorizedException;
import pl.krakow.up.managementserver.persistence.dao.UserDao;
import pl.krakow.up.managementserver.persistence.model.User;

import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;
    private final EncryptionService encryptionService;

    public UserService(final UserDao userDao,
                       final EncryptionService encryptionService) {
        this.userDao = userDao;
        this.encryptionService = encryptionService;
    }

    public User authorizeUser(final String login, final String password) {

        final Optional<User> optionalUser = this.userDao.findByLogin(login);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException.UserNotFoundException("User with login [" + login + "] not exists");
        }

        final User user = optionalUser.get();

        if (!this.encryptionService.compare(password, user.getPassword())) {
            throw new UnauthorizedException.UserUnauthorizedException("Password incorrect for user " + login);
        }

        return user;
    }

}
