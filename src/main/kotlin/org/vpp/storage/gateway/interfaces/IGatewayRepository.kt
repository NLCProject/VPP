package org.vpp.storage.gateway.interfaces

import org.springframework.stereotype.Service
import org.vpp.storage.framework.ICrudlRepository
import org.vpp.storage.gateway.GatewayEntity
import java.util.*

@Service
interface IGatewayRepository : ICrudlRepository<GatewayEntity> {

    fun findBySerialNumber(serialNumber: String): Optional<GatewayEntity>
}
