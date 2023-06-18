package org.vpp.dto

import kotlinx.serialization.Serializable
import javax.persistence.MappedSuperclass

@Serializable
@MappedSuperclass
abstract class Dto {
    lateinit var id: String
    var timestampCreated: Long = 0
    lateinit var dateTimeCreated: String
    var timestampLastModified: Long? = null
    var dateTimeLastModified: String? = null
}
