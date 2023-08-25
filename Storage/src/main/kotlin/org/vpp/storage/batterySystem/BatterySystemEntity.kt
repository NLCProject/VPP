package org.vpp.storage.batterySystem

import org.vpp.storage.consumerGroup.ConsumerGroupEntity
import org.vpp.storage.framework.DistributedEntity
import org.vpp.storage.gateway.GatewayEntity
import org.vpp.storage.measurement.MeasurementEntity
import javax.persistence.*

@Entity
@Table(name = "battery_system")
class BatterySystemEntity : DistributedEntity() {

    @Enumerated(value = EnumType.STRING)
    lateinit var status: SystemStatus

    @Column(nullable = false)
    lateinit var manufacturer: String

    @Column(nullable = false)
    lateinit var serialNumber: String

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JoinColumn(name = "consumer_group_id")
    lateinit var group: ConsumerGroupEntity

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE], mappedBy = "system")
    var measurements: MutableList<MeasurementEntity> = mutableListOf()

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gateway_id")
    lateinit var gateway: GatewayEntity
}
