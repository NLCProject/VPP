package org.vpp.storage.gateway

import org.vpp.storage.framework.DistributedEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "gateway")
class GatewayEntity : DistributedEntity() {

    @Column(nullable = false)
    var gatewayPort: Int = 0

    @Column(nullable = false)
    var websocketPort: Int = 0

    @Column(nullable = false)
    lateinit var gatewayUrl: String

    @Column(nullable = false)
    lateinit var gatewayHost: String

    @Column(nullable = false)
    lateinit var serialNumber: String
}