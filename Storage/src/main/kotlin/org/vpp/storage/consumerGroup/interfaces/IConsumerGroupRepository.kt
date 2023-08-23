package org.vpp.storage.consumerGroup.interfaces

import org.springframework.stereotype.Service
import org.vpp.storage.consumerGroup.ConsumerGroupEntity
import org.vpp.storage.framework.ICrudlRepository

@Service
interface IConsumerGroupRepository : ICrudlRepository<ConsumerGroupEntity>
