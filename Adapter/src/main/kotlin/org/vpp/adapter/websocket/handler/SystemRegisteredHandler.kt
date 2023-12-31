package org.vpp.adapter.websocket.handler

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.adapter.websocket.dto.SystemRegistered
import org.vpp.storage.batterySystem.BatterySystemEntity
import org.vpp.storage.batterySystem.BatterySystemRepository
import org.vpp.storage.batterySystem.SystemStatus
import org.vpp.storage.gateway.GatewayRepository
import org.vpp.utils.serialzation.JsonSerialization
import org.vpp.utils.validation.ValidationUtil

@Service
class SystemRegisteredHandler @Autowired constructor(
    private val gatewayRepository: GatewayRepository,
    private val batterySystemRepository: BatterySystemRepository
) : MessageHandler, JsonSerialization() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handle(message: String) {
        logger.info("Handling system register message")
        val dto = decode<SystemRegistered>(data = message)
        ValidationUtil.verifyIfEmptyAndThrow(value = dto.serialNumber)

        val optional = gatewayRepository.findBySerialNumber(serialNumber = dto.gatewaySerialNumber)
        if (!optional.isPresent)
            return logger.warn("Gateway with serial number '${dto.gatewaySerialNumber}' not found")

        val client = BatterySystemEntity().apply {
            this.gateway = optional.get()
            this.status = SystemStatus.ONLINE
            this.serialNumber = dto.serialNumber
            this.manufacturer = dto.manufacturer
        }

        batterySystemRepository.save(entity = client)
        logger.info("Client with serial number '${dto.serialNumber}' now registered")
    }
}
