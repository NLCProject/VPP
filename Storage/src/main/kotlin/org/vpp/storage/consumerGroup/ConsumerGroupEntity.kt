package org.vpp.storage.consumerGroup

import org.vpp.storage.batterySystem.BatterySystemEntity
import org.vpp.storage.framework.DistributedEntity
import javax.persistence.*

@Entity
@Table(name = "consumer_group")
class ConsumerGroupEntity : DistributedEntity() {

    @Enumerated(value = EnumType.STRING)
    var mode: ConsumerMode = ConsumerMode.None

    @Enumerated(value = EnumType.STRING)
    var wiring: WiringMode = WiringMode.Unknown

    @Column(nullable = false)
    lateinit var name: String

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE], mappedBy = "group")
    var systems = mutableListOf<BatterySystemEntity>()

    @Column(nullable = false)
    lateinit var gatewayId: String
}
