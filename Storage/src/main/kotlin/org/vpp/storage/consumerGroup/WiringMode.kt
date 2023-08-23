package org.vpp.storage.consumerGroup

import kotlinx.serialization.Serializable

@Serializable
enum class WiringMode {
    Serial,
    Parallel,
    Unknown
}
