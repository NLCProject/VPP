package org.vpp.storageApi.gateway

import kotlinx.serialization.Serializable
import org.vpp.storageApi.batterySytem.BatterySystemDto
import org.vpp.storageApi.consumerGroup.ConsumerGroupDto
import org.vpp.storageApi.dto.Dto

@Serializable
class GatewayDto : Dto() {
    lateinit var serialNumber: String
    lateinit var groups: List<ConsumerGroupDto>
    lateinit var systems: List<BatterySystemDto>
}
