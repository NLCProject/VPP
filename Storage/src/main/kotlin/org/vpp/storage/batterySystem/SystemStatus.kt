package org.vpp.storage.batterySystem

import kotlinx.serialization.Serializable

@Serializable
enum class SystemStatus {
    UNKNOWN,
    ONLINE,
    STANDBY,
    OFFLINE
}
