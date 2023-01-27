package pl.krakow.up.managementserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.krakow.up.managementserver.api.dto.ClientDto;
import pl.krakow.up.managementserver.authentication.Authenticate;
import pl.krakow.up.managementserver.controller.advice.ControllerAdviceProvider;
import pl.krakow.up.managementserver.exception.BadRequestException;
import pl.krakow.up.managementserver.mapper.ClientMapper;
import pl.krakow.up.managementserver.persistence.model.Client;
import pl.krakow.up.managementserver.service.ClientService;

import java.util.Optional;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/client")
public class ClientManageController implements ControllerAdviceProvider {

    private final ClientService clientService;

    public ClientManageController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @Authenticate
    @PutMapping("/add")
    public ResponseEntity<ClientDto> add(@RequestBody final ClientDto clientDto) {

        if (isNull(clientDto)
                || isNull(clientDto.getFirstName())
                || isNull(clientDto.getLastName())
                || isNull(clientDto.getPostCode())
                || isNull(clientDto.getCity())
                || isNull(clientDto.getBuildingNumber())
                || isNull(clientDto.getPassword())) {
            throw new BadRequestException("missing data of client");
        }

        final Client client = ClientMapper.INSTANCE.map(clientDto);
        client.setLastUpdate(System.currentTimeMillis());
        client.setFull(Boolean.FALSE);

        final Client savedClient = this.clientService.save(client);

        final ClientDto createdClient = ClientMapper.INSTANCE.map(savedClient);

        return ResponseEntity.ok(createdClient);
    }

    @Authenticate
    @PostMapping("/modify")
    public ResponseEntity<ClientDto> modify(@RequestBody final ClientDto clientDto) {

        if (isNull(clientDto)) {
            throw new BadRequestException("ClientDto is null");
        }

        if (isNull(clientDto.getId())) {
            throw new BadRequestException("ClientId is null");
        }

        final Client client = this.clientService.getById(clientDto.getId());

        Optional.ofNullable(clientDto.getFirstName()).ifPresent(client::setFirstName);
        Optional.ofNullable(clientDto.getLastName()).ifPresent(client::setLastName);
        Optional.ofNullable(clientDto.getPostCode()).ifPresent(client::setPostCode);
        Optional.ofNullable(clientDto.getCity()).ifPresent(client::setCity);
        Optional.ofNullable(clientDto.getBuildingNumber()).ifPresent(client::setBuildingNumber);
        Optional.ofNullable(clientDto.getPhoneNumber()).ifPresent(client::setPhoneNumber);
        Optional.ofNullable(clientDto.getPassword()).ifPresent(client::setPassword);

        client.setStreet(clientDto.getStreet());
        client.setFlatNumber(clientDto.getFlatNumber());

        client.setLastUpdate(System.currentTimeMillis());

        final Client savedClient = this.clientService.save(client);

        final ClientDto result = ClientMapper.INSTANCE.map(savedClient);

        return ResponseEntity.ok(result);
    }

    @Authenticate
    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<String> delete(@PathVariable final Long clientId) {

        if (isNull(clientId)) {
            throw new BadRequestException("ClientId is null");
        }

        final Client client = this.clientService.getById(clientId);
        this.clientService.delete(client);

        return ResponseEntity.ok("Client deleted");
    }
}
