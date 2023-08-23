package org.vpp.adapter

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.adapter.http.GatewayRestAdapter
import org.vpp.adapter.websocket.WebsocketAdapter
import org.vpp.storage.gateway.GatewayEntity
import org.vpp.storage.gateway.GatewayRepository
import org.vpp.utils.validation.ValidationUtil

@Service
class GatewayAdapter @Autowired constructor(
    private val websocketAdapter: WebsocketAdapter,
    private val gatewayRepository: GatewayRepository,
    private val gatewayRestAdapter: GatewayRestAdapter
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun persistGateway(dto: GatewayAdapterDto) {
        logger.info("Persisting gateway with serial number '${dto.serialNumber}'")
        ValidationUtil.verifyIfEmptyAndThrow(value = dto.gatewayUrl)
        ValidationUtil.verifyIfEmptyAndThrow(value = dto.gatewayHost)
        ValidationUtil.verifyIfEmptyAndThrow(value = dto.serialNumber)
        ValidationUtil.verifyIfValueZeroOrLowerAndThrow(value = dto.gatewayPort)

        val optional = gatewayRepository.findBySerialNumber(serialNumber = dto.serialNumber)
        if (optional.isPresent)
            return logger.info("Gateway with serial number '${dto.serialNumber}' already registered")

        var gateway = GatewayEntity().apply {
            this.gatewayHost = dto.gatewayHost
            this.gatewayPort = dto.gatewayPort
            this.serialNumber = dto.serialNumber
            this.websocketPort = dto.websocketPort
            this.gatewayUrl = dto.gatewayUrl.removePrefix("/")
        }

        gateway = gatewayRepository.save(entity = gateway)
        logger.info("Gateway with serial number '${dto.serialNumber}' now registered")
        websocketAdapter.connect(gateway = gateway)
        gatewayRestAdapter.requestSystems(gateway = gateway)
        gatewayRestAdapter.requestGroups(gateway = gateway)
    }
}
