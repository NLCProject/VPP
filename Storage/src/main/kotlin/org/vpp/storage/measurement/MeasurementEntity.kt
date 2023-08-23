package org.vpp.storage.measurement

import org.vpp.storage.batterySystem.BatterySystemEntity
import org.vpp.storage.framework.DistributedEntity
import javax.persistence.*

@Entity
@Table(name = "measurement")
class MeasurementEntity : DistributedEntity() {

    @Column(nullable = false)
    var value: Double = 0.0

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JoinColumn(name = "system_id")
    lateinit var system: BatterySystemEntity
}
