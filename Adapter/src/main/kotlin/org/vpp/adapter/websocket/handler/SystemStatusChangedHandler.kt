package org.vpp.adapter.websocket.handler

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.adapter.websocket.dto.SystemStatusChanged
import org.vpp.storage.batterySystem.BatterySystemRepository
import org.vpp.utils.serialzation.JsonSerialization
import org.vpp.utils.validation.ValidationUtil

@Service
class SystemStatusChangedHandler @Autowired constructor(
    private val batterySystemRepository: BatterySystemRepository
) : MessageHandler, JsonSerialization() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handle(message: String) {
        logger.info("Handling system status change message")
        val dto = decode<SystemStatusChanged>(data = message)
        ValidationUtil.verifyIfEmptyAndThrow(value = dto.serialNumber)

        val optional = batterySystemRepository.findBySerialNumber(serialNumber = dto.serialNumber)
        if (!optional.isPresent)
            return logger.warn("Client with serial number '${dto.serialNumber}' not found")

        val client = optional.get()
        client.status = dto.status
        batterySystemRepository.save(entity = client)
        logger.info("System status changed for client with serial number '${dto.serialNumber}'")
    }
}
