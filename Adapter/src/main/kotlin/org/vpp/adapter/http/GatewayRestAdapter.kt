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
    private val gatewayRepository: GatewayRepository,
    private val consumerGroupService: ConsumerGroupService,
    private val batterySystemRepository: BatterySystemRepository
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
                    val optional = batterySystemRepository.findByIdOptional(it.id)
                    if (optional.isPresent) {
                        logger.info("Existing system with ID '${it.id}' found")
                        val entity = optional.get()
                        entity.status = it.status
                        entity.manufacturer = it.manufacturer
                        entity.serialNumber = it.serialNumber
                        entity.group = consumerGroupService.findOrCreateGroup(group = it.group!!, gateway = gateway)
                        batterySystemRepository.save(entity)
                        return
                    }

                    val entity = BatterySystemEntity()
                    entity.id = it.id
                    entity.gateway = gateway
                    entity.status = it.status
                    entity.manufacturer = it.manufacturer
                    entity.serialNumber = it.serialNumber
                    entity.group = consumerGroupService.findOrCreateGroup(group = it.group!!, gateway = gateway)

                    logger.info("Saving client with serial number '${entity.serialNumber}'")
                    batterySystemRepository.save(entity)
                }
        } catch (exception: Exception) {
            logger.error(
                "Error while requesting systems from gateway with serial number '${gateway.serialNumber}' " +
                    "| ${exception.message}"
            )
        }
    }

    fun requestGroups(gateway: GatewayEntity) {
        try {
            logger.info("Requesting consumer groups from gateway with serial number '${gateway.serialNumber}'")
            val uri = "http://${gateway.gatewayHost}:${gateway.gatewayPort}/api/public/consumer/findAll"
            logger.info("Connecting to gateway via URL '$uri'")
            val response = RestTemplate().getForEntity(uri, String::class.java)
            logger.info("Response code from gateway | ${response.statusCode}")

            response.body ?: return logger.error("Empty response body received")
            decodeList<ConsumerGroupDto>(data = response.body!!)
                .forEach { consumerGroupService.findOrCreateGroup(group = it, gateway = gateway) }
        } catch (exception: Exception) {
            logger.error(
                "Error while requesting systems from consumer groups with serial number '${gateway.serialNumber}' " +
                    "| ${exception.message}"
            )
        }
    }

    fun changeConsumerMode(groupId: String, gatewayId: String, mode: ConsumerMode) {
        try {
            logger.info("Changing consumer mode of group ID '$groupId' to mode '$mode'")
            val gateway = gatewayRepository.findById(gatewayId)
            val uri = "http://${gateway.gatewayHost}:${gateway.gatewayPort}/api/public/consumer/changeConsumerMode?" +
                "groupId=$groupId&mode=$mode"
            logger.info("Connecting to gateway via URL '$uri'")
            val response = RestTemplate().postForEntity(uri, null, String::class.java)
            logger.info("Response code from gateway | ${response.statusCode}")
            requestSystems(gateway)
            requestGroups(gateway)
        } catch (exception: Exception) {
            logger.error("Error while changing consumer mode of group ID '$groupId' | ${exception.message}")
        }
    }
}
