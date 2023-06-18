package org.vpp.adapter.websocket.dto

import kotlinx.serialization.Serializable
import org.vpp.adapter.websocket.dto.helper.MessageType

@Serializable
class VoltageMeasurement {
    var value: Double = 0.0
    lateinit var text: String
    lateinit var type: MessageType
    lateinit var serialNumber: String
    lateinit var manufacturer: String
    lateinit var gatewaySerialNumber: String
}
