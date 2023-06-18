package org.vpp.adapter

class GatewayAdapterDto {
    var gatewayPort: Int = 0
    var websocketPort: Int = 0
    lateinit var gatewayUrl: String
    lateinit var gatewayHost: String
    lateinit var serialNumber: String
}
