package org.vpp.adapter

import org.gateway.utils.controller.CrossOriginData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.vpp.utils.controller.ControllerCallback

@Controller
@Transactional
@RequestMapping(path = ["/gateway"])
@CrossOrigin(origins = [CrossOriginData.origins], allowedHeaders = [CrossOriginData.allowedHeaders])
class GatewayAdapterController @Autowired constructor(
    private val gatewayAdapter: GatewayAdapter
) {

    @PostMapping(value = ["/register"])
    fun register(
        @RequestBody dto: GatewayAdapterDto
    ): ResponseEntity<*> = ControllerCallback.postOperation {
        gatewayAdapter.persistGateway(dto = dto)
    }
}
