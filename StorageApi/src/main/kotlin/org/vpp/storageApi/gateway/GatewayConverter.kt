package org.vpp.storageApi.gateway

import org.vpp.storage.consumerGroup.ConsumerGroupEntity
import org.vpp.storage.gateway.GatewayEntity
import org.vpp.storageApi.batterySytem.BatterySystemConverter
import org.vpp.storageApi.consumerGroup.ConsumerGroupConverter
import org.vpp.storageApi.dto.DtoConverter

object GatewayConverter {

    fun convert(entity: GatewayEntity, groups: List<ConsumerGroupEntity>): GatewayDto = GatewayDto().apply {
        this.serialNumber = entity.serialNumber
        this.groups = groups.map { ConsumerGroupConverter.convertOnSubLevel(it) }
        this.systems = entity.systems.map { BatterySystemConverter.convertOnSubLevel(it) }
        DtoConverter.convert(entity = entity, dto = this)
    }
}
