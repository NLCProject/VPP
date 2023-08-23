package org.vpp.storageApi.gateway

import org.vpp.storage.gateway.GatewayEntity
import org.vpp.storageApi.batterySytem.BatterySystemConverter
import org.vpp.storageApi.consumerGroup.ConsumerGroupConverter
import org.vpp.storageApi.dto.DtoConverter

object GatewayConverter {

    fun convert(entity: GatewayEntity): GatewayDto = GatewayDto().apply {
        serialNumber = entity.serialNumber
        groups = entity.groups.map { ConsumerGroupConverter.convertOnSubLevel(it) }
        systems = entity.systems.map { BatterySystemConverter.convertOnSubLevel(it) }
        DtoConverter.convert(entity = entity, dto = this)
    }
}
