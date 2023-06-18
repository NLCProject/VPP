package org.vpp.storage.client.interfaces

import org.springframework.stereotype.Service
import org.vpp.storage.client.ClientEntity
import org.vpp.storage.framework.ICrudlRepository
import java.util.*

@Service
interface IClientRepository : ICrudlRepository<ClientEntity> {

    fun findBySerialNumber(serialNumber: String): Optional<ClientEntity>
}
