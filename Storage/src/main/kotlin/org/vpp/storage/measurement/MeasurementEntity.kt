package org.vpp.storage.measurement

import org.vpp.storage.client.ClientEntity
import org.vpp.storage.framework.DistributedEntity
import javax.persistence.*

@Entity
@Table(name = "measurement")
class MeasurementEntity : DistributedEntity() {

    @Column(nullable = false)
    var value: Double = 0.0

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "client_id")
    lateinit var client: ClientEntity
}
