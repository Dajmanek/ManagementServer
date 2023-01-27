package pl.krakow.up.managementserver.service;

import org.springframework.stereotype.Service;
import pl.krakow.up.managementserver.exception.NotFoundException;
import pl.krakow.up.managementserver.exception.UnauthorizedException;
import pl.krakow.up.managementserver.persistence.dao.ClientDao;
import pl.krakow.up.managementserver.persistence.model.Client;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientDao clientDao;

    public ClientService(final ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<Client> findAll() {
        return this.clientDao.findAll();
    }

    public Optional<Client> findById(final Long clientId) {
        return this.clientDao.findById(clientId);
    }

    public void delete(final Client client) {
        this.clientDao.delete(client);
    }

    public Client save(final Client client) {
        return this.clientDao.save(client);
    }

    public Client authorizeClient(final Long clientId, final String password) {

        final Client client = this.getById(clientId);

        if (!client.getPassword().equals(password)) {
            throw new UnauthorizedException.ClientUnauthorizedException("authorizeClient() wrong password for client " + client.getId());
        }

        return client;
    }

    public Client getById(final Long clientId) {
        final Optional<Client> optionalClient = this.findById(clientId);
        if (optionalClient.isEmpty()) {
            throw new NotFoundException.ClientNotFoundException("authorizeClient() client with id " + clientId + " not found");
        }
        return optionalClient.get();
    }

}
