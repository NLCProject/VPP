package org.vpp.storageApi.batterySytem

import org.vpp.storage.batterySystem.BatterySystemEntity
import org.vpp.storageApi.consumerGroup.ConsumerGroupConverter
import org.vpp.storageApi.dto.DtoConverter

object BatterySystemConverter {

    fun convert(entity: BatterySystemEntity): BatterySystemDto = BatterySystemDto().apply {
        status = entity.status
        manufacturer = entity.manufacturer
        serialNumber = entity.serialNumber
        group = ConsumerGroupConverter.convertOnSubLevel(entity.group)
        DtoConverter.convert(entity = entity, dto = this)
    }
}
