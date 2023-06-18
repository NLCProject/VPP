package org.vpp.internalApi.dto

import org.vpp.dto.SystemStatus

class ClientDto(
    var id: String,
    val value: Double = 0.0,
    var serialNumber: String,
    var manufacturer: String,
    var status: SystemStatus
)
