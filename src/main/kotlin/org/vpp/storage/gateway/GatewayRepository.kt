package org.vpp.storage.gateway

import org.vpp.storage.framework.Repository
import org.vpp.storage.gateway.interfaces.IGatewayRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class GatewayRepository @Autowired constructor(
    private val repository: IGatewayRepository
) : Repository<GatewayEntity>(repository = repository) {

    fun findAllBySerialNumber(serialNumber: String): Optional<GatewayEntity> =
        repository.findAllBySerialNumber(serialNumber = serialNumber)
}
