package org.vpp.storage.batterySystem

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.storage.batterySystem.interfaces.IBatterySystemRepository
import org.vpp.storage.framework.Repository
import java.util.*

@Service
class BatterySystemRepository @Autowired constructor(
    private val repository: IBatterySystemRepository
) : Repository<BatterySystemEntity>(repository = repository) {

    fun findByIdOptional(id: String): Optional<BatterySystemEntity> = repository.findById(id)

    fun findBySerialNumber(serialNumber: String): Optional<BatterySystemEntity> =
        repository.findBySerialNumber(serialNumber = serialNumber)
}
