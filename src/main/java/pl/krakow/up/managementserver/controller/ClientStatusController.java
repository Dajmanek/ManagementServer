package pl.krakow.up.managementserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.krakow.up.managementserver.api.dto.ClientDto;
import pl.krakow.up.managementserver.api.dto.ClientListDto;
import pl.krakow.up.managementserver.controller.advice.ControllerAdviceProvider;
import pl.krakow.up.managementserver.exception.BadRequestException;
import pl.krakow.up.managementserver.exception.NotFoundException;
import pl.krakow.up.managementserver.exception.UnauthorizedException;
import pl.krakow.up.managementserver.mapper.ClientMapper;
import pl.krakow.up.managementserver.persistence.dao.ClientDao;
import pl.krakow.up.managementserver.persistence.dao.UserDao;
import pl.krakow.up.managementserver.persistence.model.Client;
import pl.krakow.up.managementserver.persistence.model.User;
import pl.krakow.up.managementserver.service.EncryptionService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

@Controller
@RequestMapping("/client/status")
public class ClientStatusController implements ControllerAdviceProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientStatusController.class);

    private final ClientDao clientDao;
    private final UserDao userDao;
    private final EncryptionService encryptionService;

    public ClientStatusController(final ClientDao clientDao,
                                  final UserDao userDao,
                                  final EncryptionService encryptionService) {
        this.clientDao = clientDao;
        this.userDao = userDao;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/update")
    public void updateStatus(final Long clientId,
                       final String password,
                       final Double value) {

        if (isNull(clientId) || isNull(password) || isNull(value)) {
            throw new BadRequestException("updateStatus() clientId [" + clientId + "], password [****] or value ["
                    + value + "] is null");
        }

        final Optional<Client> optionalClient = this.clientDao.findById(clientId);

        if (optionalClient.isEmpty()) {
            throw new NotFoundException.ClientNotFoundException("updateStatus() client with id " + clientId + " not found");
        }

        final Client client = optionalClient.get();

        // authentication client (router trigger request)
        if (!client.getPassword().equals(password)) {
            throw new UnauthorizedException.ClientUnauthorizedException("updateStatus() wrong password for client " + clientId);
        }

        final Boolean full = value > 0.85 ? TRUE : FALSE;

        if (Objects.equals(client.getFull(), full)) {
            return;
        }

        client.setFull(full);
        this.clientDao.save(client);
    }

    @GetMapping("")
    public ResponseEntity<ClientListDto> status(final String login,
                                                final String password) {

        if (isNull(login) || isNull(password)) {
            throw new BadRequestException("status() login [" + login + "] or password [****] is null");
        }

        final Optional<User> optionalUser = this.userDao.findByLogin(login);

        if (optionalUser.isEmpty()) {
            throw new NotFoundException.UserNotFoundException("status() user with login [" + login + "] not exists");
        }

        final User user = optionalUser.get();

        // authentication user (desktop app)
        if (!this.encryptionService.compare(password, user.getPassword())) {
            throw new UnauthorizedException.UserUnauthorizedException("status() password incorrect for user " + login);
        }

        final List<Client> clients = this.clientDao.findAll();

        final List<ClientDto> clientDtos = clients.stream()
                .map(ClientMapper.INSTANCE::map).toList();

        final ClientListDto clientListDto = new ClientListDto(clientDtos);

        return ResponseEntity.ok(clientListDto);
    }

}
