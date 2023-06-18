package org.vpp.adapter.http

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.vpp.dto.BatterySystemDto
import org.vpp.storage.client.ClientEntity
import org.vpp.storage.client.ClientRepository
import org.vpp.storage.gateway.GatewayEntity
import org.vpp.utils.serialzation.JsonSerialization

@Service
class GatewayRestAdapter @Autowired constructor(
    private val clientRepository: ClientRepository
) : JsonSerialization() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun requestClientsFromGateway(gateway: GatewayEntity) {
        try {
            logger.info("Requesting clients from gateway with serial number '${gateway.serialNumber}'")
            val uri = "http://${gateway.gatewayHost}:${gateway.gatewayPort}/api/public/system/findAll"
            logger.info("Connecting to gateway via URL '$uri'")
            val response = RestTemplate().getForEntity(uri, String::class.java)
            logger.info("Response code from VPP | ${response.statusCode}")

            response.body ?: return logger.error("Empty response body received")
            decodeList<BatterySystemDto>(data = response.body!!)
                .forEach {
                    val client = ClientEntity().apply {
                        this.gateway = gateway
                        this.status = it.status
                        this.manufacturer = it.manufacturer
                        this.serialNumber = it.serialNumber
                    }

                    logger.info("Saving client with serial number '${client.serialNumber}'")
                    clientRepository.save(entity = client)
                }
        } catch (exception: Exception) {
            logger.error(
                "Error while requesting clients from gateway with serial number '${gateway.serialNumber}' " +
                    "| ${exception.message}"
            )
        }
    }
}
