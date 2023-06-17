package org.vpp.api.gateway

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.gateway.utils.controller.CrossOriginData
import org.vpp.api.gateway.dto.GatewayDtoMapper
import org.vpp.utils.controller.ControllerCallback

@Controller
@Transactional
@RequestMapping(path = ["/api/internal/client"])
@CrossOrigin(origins = [CrossOriginData.origins], allowedHeaders = [CrossOriginData.allowedHeaders])
class InternalApiController @Autowired constructor(
    private val gatewayDtoMapper: GatewayDtoMapper
) {

    @GetMapping(value = ["/findAll"])
    fun findAll(): ResponseEntity<*> = ControllerCallback.getOperation {
        gatewayDtoMapper.mapAll()
    }
}
