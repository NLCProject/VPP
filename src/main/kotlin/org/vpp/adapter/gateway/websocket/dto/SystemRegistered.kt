package org.vpp.adapter.gateway.websocket.dto

import kotlinx.serialization.Serializable
import org.vpp.adapter.gateway.websocket.dto.helper.MessageType

@Serializable
class SystemRegistered {
    lateinit var text: String
    lateinit var type: MessageType
    lateinit var serialNumber: String
    lateinit var manufacturer: String
    lateinit var gatewaySerialNumber: String
}
