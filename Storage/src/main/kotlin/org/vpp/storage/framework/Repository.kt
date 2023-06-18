package org.vpp.storage.framework

import org.vpp.utils.serialzation.JsonSerialization
import org.vpp.utils.validation.ValidationUtil

abstract class Repository<ENTITY : DistributedEntity>(
    private val repository: ICrudlRepository<ENTITY>,
) : RepositoryBaseOperations<ENTITY>, JsonSerialization() {

    override fun findAll(): List<ENTITY> = repository.findAll().toList()

    override fun save(entity: ENTITY): ENTITY {
        ValidationUtil.verifyIfEmptyAndThrow(value = entity.id)
        return repository.save(entity)
    }
}
