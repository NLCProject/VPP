package org.vpp.storageApi.consumerGroup

import org.vpp.storage.batterySystem.BatterySystemEntity
import org.vpp.storage.consumerGroup.ConsumerGroupEntity
import org.vpp.storage.consumerGroup.WiringMode
import org.vpp.storageApi.batterySytem.BatterySystemConverter
import org.vpp.storageApi.dto.DtoConverter
import kotlin.math.roundToInt

object ConsumerGroupConverter {

    fun convert(entity: ConsumerGroupEntity): ConsumerGroupDto = ConsumerGroupDto().apply {
        name = entity.name
        mode = entity.mode
        wiring = entity.wiring
        gatewayId = entity.gatewayId
        voltage = (calculateVoltage(entity) * 100.0).roundToInt() / 100.0
        systems = entity.systems.map { BatterySystemConverter.convert(it) }
        DtoConverter.convert(entity = entity, dto = this)
    }

    fun convertOnSubLevel(entity: ConsumerGroupEntity): ConsumerGroupDto = ConsumerGroupDto().apply {
        name = entity.name
        mode = entity.mode
        wiring = entity.wiring
        gatewayId = entity.gatewayId
        voltage = (calculateVoltage(entity) * 100.0).roundToInt() / 100.0
        DtoConverter.convert(entity = entity, dto = this)
    }

    private fun calculateVoltage(entity: ConsumerGroupEntity): Double = when (entity.wiring) {
        WiringMode.Unknown -> 0.0
        WiringMode.Serial -> entity.systems.sumOf { getLatestMeasurement(it) }
        WiringMode.Parallel -> entity
            .systems
            .map { getLatestMeasurement(it) }
            .filter { it > 0 }
            .minOrNull()
            ?: 0.0
    }

    private fun getLatestMeasurement(system: BatterySystemEntity): Double {
        system.measurements.sortByDescending { it.timestampCreated }
        return system.measurements.firstOrNull()?.value ?: 0.0
    }
}
