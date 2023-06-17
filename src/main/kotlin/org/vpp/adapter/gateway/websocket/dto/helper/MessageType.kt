package org.vpp.adapter.gateway.websocket.dto.helper

import kotlinx.serialization.Serializable

@Serializable
enum class MessageType {
    SystemDetected,
    SystemRegistered,
    SystemStatusChanged,
    VoltageMeasurement
}
