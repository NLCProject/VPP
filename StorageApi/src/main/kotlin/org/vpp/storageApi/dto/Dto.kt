package org.vpp.storageApi.dto

import kotlinx.serialization.Serializable
import javax.persistence.MappedSuperclass

@Serializable
@MappedSuperclass
abstract class Dto {
    lateinit var id: String
    var timestampCreated: Long = 0
    var timestampLastModified: Long? = null
}
