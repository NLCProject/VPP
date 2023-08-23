package org.vpp.adapter.http

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.vpp.storage.batterySystem.BatterySystemEntity
import org.vpp.storage.batterySystem.BatterySystemRepository
import org.vpp.storage.consumerGroup.ConsumerGroupRepository
import org.vpp.storage.consumerGroup.ConsumerMode
import org.vpp.storage.gateway.GatewayEntity
import org.vpp.storage.gateway.GatewayRepository
import org.vpp.storageApi.batterySytem.BatterySystemDto
import org.vpp.storageApi.consumerGroup.ConsumerGroupDto
import org.vpp.storageApi.consumerGroup.ConsumerGroupService
import org.vpp.utils.serialzation.JsonSerialization

@Service
class GatewayRestAdapter @Autowired constructor(
    private val consumerGroupService: ConsumerGroupService,
    private val batterySystemRepository: BatterySystemRepository,
    private val consumerGroupRepository: ConsumerGroupRepository
) : JsonSerialization() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun requestSystems(gateway: GatewayEntity) {
        try {
            logger.info("Requesting systems from gateway with serial number '${gateway.serialNumber}'")
            val uri = "http://${gateway.gatewayHost}:${gateway.gatewayPort}/api/public/system/findAll"
            logger.info("Connecting to gateway via URL '$uri'")
            val response = RestTemplate().getForEntity(uri, String::class.java)
            logger.info("Response code from gateway | ${response.statusCode}")

            response.body ?: return logger.error("Empty response body received")
            decodeList<BatterySystemDto>(data = response.body!!)
                .forEach {
                    val client = BatterySystemEntity().apply {
                        this.gateway = gateway
                        this.status = it.status
                        this.manufacturer = it.manufacturer
                        this.serialNumber = it.serialNumber
                        this.group = consumerGroupService.findOrCreateGroup(group = it.group, gateway = gateway)
                    }

                    logger.info("Saving client with serial number '${client.serialNumber}'")
                    batterySystemRepository.save(entity = client)
                }
        } catch (exception: Exception) {
            logger.error(
                "Error while requesting systems from gateway with serial number '${gateway.serialNumber}' " +
                    "| ${exception.message}"
            )
        }
    }

    fun changeConsumerMode(groupId: String, mode: ConsumerMode) {
        try {
            logger.info("Changing consumer mode of group ID '$groupId' to mode '$mode'")
            val gateway = consumerGroupRepository.findById(groupId).gateway
            val uri = "http://${gateway.gatewayHost}:${gateway.gatewayPort}/api/public/consumer/changeConsumerMode?" +
                "groupId=$groupId&mode=$mode"
            logger.info("Connecting to gateway via URL '$uri'")
            val response = RestTemplate().postForEntity(uri, null, String::class.java)
            logger.info("Response code from gateway | ${response.statusCode}")
        } catch (exception: Exception) {
            logger.error("Error while changing consumer mode of group ID '$groupId' | ${exception.message}")
        }
    }
}
