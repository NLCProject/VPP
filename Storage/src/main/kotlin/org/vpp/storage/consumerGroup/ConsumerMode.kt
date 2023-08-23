package org.vpp.storage.consumerGroup

import kotlinx.serialization.Serializable

@Serializable
enum class ConsumerMode {
    None,
    Loading,
    Consuming
}
