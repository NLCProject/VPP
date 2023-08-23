package org.vpp.storageApi.gateway

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.storage.gateway.GatewayRepository

@Service
class GatewayService @Autowired constructor(
    private val repository: GatewayRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun findAll(): List<GatewayDto> = repository
        .findAll()
        .map { GatewayConverter.convert(entity = it) }
}
