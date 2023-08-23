package org.vpp.storageApi.consumerGroup

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.storage.consumerGroup.ConsumerGroupEntity
import org.vpp.storage.consumerGroup.interfaces.IConsumerGroupRepository
import org.vpp.storage.gateway.GatewayEntity

@Service
class ConsumerGroupService @Autowired constructor(
    private val repository: IConsumerGroupRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun findOrCreateGroup(group: ConsumerGroupDto, gateway: GatewayEntity): ConsumerGroupEntity {
        val optional = repository.findById(group.id)
        if (optional.isPresent) {
            logger.info("Existing consuming group with ID '${group.id}' found")
            val entity = optional.get()
            entity.name = group.name
            entity.mode = group.mode
            entity.wiring = group.wiring
            return repository.save(entity)
        }

        logger.info("Creating consuming group with ID '${group.id}'")
        val entity = ConsumerGroupEntity().apply {
            this.id = group.id
            this.name = group.name
            this.mode = group.mode
            this.wiring = group.wiring
            this.gatewayId = gateway.id
        }

        return repository.save(entity)
    }
}
