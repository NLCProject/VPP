package org.vpp.storage.client

import org.vpp.dto.SystemStatus
import org.vpp.storage.framework.DistributedEntity
import org.vpp.storage.gateway.GatewayEntity
import org.vpp.storage.measurement.MeasurementEntity
import javax.persistence.*

@Entity
@Table(name = "client")
class ClientEntity : DistributedEntity() {

    @Enumerated(value = EnumType.STRING)
    lateinit var status: SystemStatus

    @Column(nullable = false)
    lateinit var serialNumber: String

    @Column(nullable = false)
    lateinit var manufacturer: String

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "gateway_id")
    lateinit var gateway: GatewayEntity

    @OneToMany(cascade = [CascadeType.MERGE], mappedBy = "client")
    var measurements: MutableList<MeasurementEntity> = mutableListOf()
}
