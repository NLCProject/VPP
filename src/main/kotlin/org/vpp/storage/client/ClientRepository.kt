package org.vpp.storage.client

import org.vpp.storage.framework.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.storage.client.interfaces.IClientRepository
import java.util.*

@Service
class ClientRepository @Autowired constructor(
    private val repository: IClientRepository
) : Repository<ClientEntity>(repository = repository) {

    fun findBySerialNumber(serialNumber: String): Optional<ClientEntity> =
        repository.findBySerialNumber(serialNumber = serialNumber)
}
