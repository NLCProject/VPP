package org.vpp.adapter.websocket.dto

import kotlinx.serialization.Serializable
import org.vpp.adapter.websocket.dto.helper.MessageType

@Serializable
class SystemDetected {
    lateinit var text: String
    lateinit var type: MessageType
    lateinit var gatewaySerialNumber: String
}
