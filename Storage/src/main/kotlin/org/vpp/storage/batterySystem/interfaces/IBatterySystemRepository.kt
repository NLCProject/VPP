package org.vpp.storage.batterySystem.interfaces

import org.springframework.stereotype.Service
import org.vpp.storage.batterySystem.BatterySystemEntity
import org.vpp.storage.framework.ICrudlRepository
import java.util.*

@Service
interface IBatterySystemRepository : ICrudlRepository<BatterySystemEntity> {

    fun findBySerialNumber(serialNumber: String): Optional<BatterySystemEntity>
}
