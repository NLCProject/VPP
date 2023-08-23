package org.vpp.storageApi.consumerGroup

import kotlinx.serialization.Serializable
import org.vpp.storage.consumerGroup.ConsumerMode
import org.vpp.storage.consumerGroup.WiringMode
import org.vpp.storageApi.batterySytem.BatterySystemDto
import org.vpp.storageApi.dto.Dto

@Serializable
class ConsumerGroupDto : Dto() {
    lateinit var name: String
    var voltage: Double = 0.0
    lateinit var mode: ConsumerMode
    lateinit var wiring: WiringMode
    var systems: List<BatterySystemDto> = emptyList()
}
