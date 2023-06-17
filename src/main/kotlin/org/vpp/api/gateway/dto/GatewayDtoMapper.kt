package org.vpp.api.gateway.dto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.storage.gateway.GatewayRepository

@Service
class GatewayDtoMapper @Autowired constructor(
    private val clientDtoMapper: ClientDtoMapper,
    private val gatewayRepository: GatewayRepository
) {

    fun mapAll(): List<GatewayDto> = gatewayRepository
        .findAll()
        .map {
            GatewayDto(
                id = it.id,
                serialNumber = it.serialNumber,
                clients = it.clients.map { client -> clientDtoMapper.map(client = client) }
            )
        }
}
