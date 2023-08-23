package org.vpp.storageApi.gateway

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.storage.consumerGroup.ConsumerGroupRepository
import org.vpp.storage.gateway.GatewayRepository

@Service
class GatewayService @Autowired constructor(
    private val gatewayRepository: GatewayRepository,
    private val consumerGroupRepository: ConsumerGroupRepository
) {

    fun findById(gatewayId: String) = gatewayRepository
        .findById(gatewayId)
        .let {
            val groups = consumerGroupRepository.findAllByGatewayId(it.id)
            GatewayConverter.convert(entity = it, groups = groups)
        }

    fun findAll(): List<GatewayDto> = gatewayRepository
        .findAll()
        .map {
            val groups = consumerGroupRepository.findAllByGatewayId(it.id)
            GatewayConverter.convert(entity = it, groups = groups)
        }
}
