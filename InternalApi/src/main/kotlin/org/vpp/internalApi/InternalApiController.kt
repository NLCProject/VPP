package org.vpp.internalApi

import org.gateway.utils.controller.CrossOriginData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.vpp.adapter.http.GatewayRestAdapter
import org.vpp.storage.consumerGroup.ConsumerMode
import org.vpp.storageApi.gateway.GatewayService
import org.vpp.utils.controller.ControllerCallback

@Controller
@Transactional
@RequestMapping(path = ["/api/internal/client"])
@CrossOrigin(origins = [CrossOriginData.origins], allowedHeaders = [CrossOriginData.allowedHeaders])
class InternalApiController @Autowired constructor(
    private val gatewayService: GatewayService,
    private val gatewayRestAdapter: GatewayRestAdapter
) {

    @GetMapping(value = ["/findAll"])
    fun findAll(): ResponseEntity<*> = ControllerCallback.getOperation {
        gatewayService.findAll()
    }

    @PostMapping(value = ["/changeConsumerMode"])
    fun changeConsumerMode(
        @RequestParam groupId: String,
        @RequestParam gatewayId: String,
        @RequestParam mode: ConsumerMode
    ): ResponseEntity<*> =
        ControllerCallback.postOperation {
            gatewayRestAdapter.changeConsumerMode(groupId = groupId, gatewayId = gatewayId, mode = mode)
        }
}
