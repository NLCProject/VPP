package org.vpp.storage.consumerGroup

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.storage.consumerGroup.interfaces.IConsumerGroupRepository
import org.vpp.storage.framework.Repository

@Service
class ConsumerGroupRepository @Autowired constructor(
    private val repository: IConsumerGroupRepository
) : Repository<ConsumerGroupEntity>(repository = repository) {

    fun findById(groupId: String): ConsumerGroupEntity = repository.findById(groupId).get()
}