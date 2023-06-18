package org.vpp.adapter.websocket.handler

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.adapter.websocket.dto.SystemRegistered
import org.vpp.dto.SystemStatus
import org.vpp.storage.client.ClientEntity
import org.vpp.storage.client.ClientRepository
import org.vpp.storage.gateway.GatewayRepository
import org.vpp.utils.serialzation.JsonSerialization
import org.vpp.utils.validation.ValidationUtil

@Service
class SystemRegisteredHandler @Autowired constructor(
    private val clientRepository: ClientRepository,
    private val gatewayRepository: GatewayRepository
) : MessageHandler, JsonSerialization() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handle(message: String) {
        logger.info("Handling system register message")
        val dto = decode<SystemRegistered>(data = message)
        ValidationUtil.verifyIfEmptyAndThrow(value = dto.serialNumber)

        val optional = gatewayRepository.findBySerialNumber(serialNumber = dto.gatewaySerialNumber)
        if (!optional.isPresent)
            return logger.warn("Gateway with serial number '${dto.gatewaySerialNumber}' not found")

        val client = ClientEntity().apply {
            this.gateway = optional.get()
            this.status = SystemStatus.ONLINE
            this.serialNumber = dto.serialNumber
            this.manufacturer = dto.manufacturer
        }

        clientRepository.save(entity = client)
        logger.info("Client with serial number '${dto.serialNumber}' now registered")
    }
}
