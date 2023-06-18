package org.vpp.dto

import kotlinx.serialization.Serializable

@Serializable
enum class SystemStatus {
    UNKNOWN,
    ONLINE,
    STANDBY,
    OFFLINE
}
