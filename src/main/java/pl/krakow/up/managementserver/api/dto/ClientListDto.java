package pl.krakow.up.managementserver.api.dto;

import java.util.List;

public class ClientListDto {

    private List<ClientDto> clients;

    public ClientListDto() {
    }

    public ClientListDto(final List<ClientDto> clients) {
        this.clients = clients;
    }

    public List<ClientDto> getClients() {
        return this.clients;
    }

    public void setClients(final List<ClientDto> clients) {
        this.clients = clients;
    }
}
