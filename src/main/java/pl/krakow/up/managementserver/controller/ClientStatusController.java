package pl.krakow.up.managementserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.krakow.up.managementserver.api.dto.ClientDto;
import pl.krakow.up.managementserver.api.dto.ClientListDto;
import pl.krakow.up.managementserver.authentication.Authenticate;
import pl.krakow.up.managementserver.controller.advice.ControllerAdviceProvider;
import pl.krakow.up.managementserver.exception.BadRequestException;
import pl.krakow.up.managementserver.mapper.ClientMapper;
import pl.krakow.up.managementserver.persistence.model.Client;
import pl.krakow.up.managementserver.service.ClientService;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

@Controller
@RequestMapping("/client/status")
public class ClientStatusController implements ControllerAdviceProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientStatusController.class);

    private final ClientService clientService;

    public ClientStatusController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/update/{clientId}/{password}/")
    public ResponseEntity<String> updateStatus(@PathVariable("clientId") final Long clientId,
                                               @PathVariable("password") final String password,
                                               final Double value) {

        LOGGER.info("updateStatus() triggered for client {} and value {}", clientId, value);

        if (isNull(clientId) || isNull(password) || isNull(value)) {
            throw new BadRequestException("updateStatus() clientId [" + clientId + "], password [****] or value ["
                    + value + "] is null");
        }

        final Client client = this.clientService.authorizeClient(clientId, password);

        final Boolean full = value > 0.85 ? TRUE : FALSE;

        client.setFull(full);
        client.setLastUpdate(System.currentTimeMillis());

        this.clientService.save(client);

        return ResponseEntity.ok("status updated");
    }

    @Authenticate
    @GetMapping("")
    public ResponseEntity<ClientListDto> status() {

        final List<Client> clients = this.clientService.findAll();

        final List<ClientDto> clientDtos = clients.stream()
                .map(ClientMapper.INSTANCE::map).toList();

        final ClientListDto clientListDto = new ClientListDto(clientDtos);

        return ResponseEntity.ok(clientListDto);
    }

}
