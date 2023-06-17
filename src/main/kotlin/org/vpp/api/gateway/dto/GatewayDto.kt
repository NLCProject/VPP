package org.vpp.api.gateway.dto

class GatewayDto(
    var id: String,
    var serialNumber: String,
    var clients: List<ClientDto>
)
