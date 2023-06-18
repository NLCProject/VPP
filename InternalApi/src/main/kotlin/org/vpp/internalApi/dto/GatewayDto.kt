package org.vpp.internalApi.dto

class GatewayDto(
    var id: String,
    var serialNumber: String,
    var clients: List<ClientDto>
)
