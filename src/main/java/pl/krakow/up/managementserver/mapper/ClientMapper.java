package pl.krakow.up.managementserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.krakow.up.managementserver.api.dto.ClientDto;
import pl.krakow.up.managementserver.persistence.model.Client;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto map(final Client client);

    Client map(final ClientDto clientDto);

}
