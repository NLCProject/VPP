package org.vpp.storage.consumerGroup

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.storage.consumerGroup.interfaces.IConsumerGroupRepository
import org.vpp.storage.framework.Repository

@Service
class ConsumerGroupRepository @Autowired constructor(
    private val repository: IConsumerGroupRepository
) : Repository<ConsumerGroupEntity>(repository = repository) {

    fun findAllByGatewayId(gatewayId: String): List<ConsumerGroupEntity> = repository.findAllByGatewayId(gatewayId)
}
