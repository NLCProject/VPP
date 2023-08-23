package org.vpp.storageApi.batterySytem

import kotlinx.serialization.Serializable
import org.vpp.storage.batterySystem.SystemStatus
import org.vpp.storageApi.consumerGroup.ConsumerGroupDto
import org.vpp.storageApi.dto.Dto

@Serializable
class BatterySystemDto : Dto() {
    var voltage: Double = 0.0
    lateinit var status: SystemStatus
    lateinit var manufacturer: String
    lateinit var serialNumber: String
    var group: ConsumerGroupDto? = null
}
