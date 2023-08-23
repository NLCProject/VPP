package org.vpp.adapter.websocket.dto.helper

import kotlinx.serialization.Serializable

@Serializable
enum class MessageType {
    SystemDetected,
    SystemRegistered,
    VoltageMeasurement,
    SystemStatusChanged,
    ConsumerGroupsChanged
}
