package org.vpp.adapter.websocket.handler

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.adapter.websocket.dto.VoltageMeasurement
import org.vpp.storage.client.ClientRepository
import org.vpp.storage.measurement.MeasurementEntity
import org.vpp.storage.measurement.MeasurementRepository
import org.vpp.utils.serialzation.JsonSerialization
import org.vpp.utils.validation.ValidationUtil

@Service
class VoltageMeasurementHandler @Autowired constructor(
    private val clientRepository: ClientRepository,
    private val measurementRepository: MeasurementRepository
) : MessageHandler, JsonSerialization() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handle(message: String) {
        logger.info("Handling voltage measurement message")
        val dto = decode<VoltageMeasurement>(data = message)
        ValidationUtil.verifyIfEmptyAndThrow(value = dto.serialNumber)

        val optional = clientRepository.findBySerialNumber(serialNumber = dto.serialNumber)
        if (!optional.isPresent)
            return logger.warn("Client with serial number '${dto.serialNumber}' not found")

        val measurement = MeasurementEntity().apply {
            this.value = dto.value
            this.client = optional.get()
        }

        measurementRepository.save(entity = measurement)
        logger.info("Measurement saved for client with serial number '${dto.serialNumber}'")
    }
}
