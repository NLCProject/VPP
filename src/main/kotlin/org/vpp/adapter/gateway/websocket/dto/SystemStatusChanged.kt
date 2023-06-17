package org.vpp.adapter.gateway.websocket.dto

import kotlinx.serialization.Serializable
import org.vpp.adapter.gateway.websocket.dto.helper.MessageType
import org.vpp.dto.SystemStatus

@Serializable
class SystemStatusChanged {
    lateinit var text: String
    lateinit var type: MessageType
    lateinit var serialNumber: String
    lateinit var manufacturer: String
    lateinit var status: SystemStatus
    lateinit var gatewaySerialNumber: String
}
