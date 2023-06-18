package org.vpp.storage.framework

interface RepositoryBaseOperations<ENTITY : DistributedEntity> {

    fun findAll(): List<ENTITY>

    fun save(entity: ENTITY): ENTITY
}
