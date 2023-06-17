package org.vpp.dto

import kotlinx.serialization.Serializable

@Serializable
class BatterySystemDto : Dto() {
    lateinit var status: SystemStatus
    lateinit var manufacturer: String
    lateinit var serialNumber: String
}
