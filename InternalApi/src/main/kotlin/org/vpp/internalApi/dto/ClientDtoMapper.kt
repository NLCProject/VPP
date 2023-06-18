package org.vpp.internalApi.dto

import org.springframework.stereotype.Service
import org.vpp.storage.client.ClientEntity

@Service
class ClientDtoMapper {

    fun map(client: ClientEntity): ClientDto = ClientDto(
        id = client.id,
        status = client.status,
        serialNumber = client.serialNumber,
        manufacturer = client.manufacturer,
        value = client.measurements.maxByOrNull { it.timestampCreated }?.value ?: 0.0
    )
}
