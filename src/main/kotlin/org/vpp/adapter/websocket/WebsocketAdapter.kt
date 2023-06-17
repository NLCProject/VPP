package org.vpp.adapter.websocket

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.vpp.storage.gateway.GatewayEntity
import java.net.URISyntaxException

@Service
class WebsocketAdapter {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun run(gateway: GatewayEntity) {
        try {
            val url = "ws://${gateway.gatewayHost}:${gateway.websocketPort}/${gateway.gatewayUrl}"
            logger.info("Connecting to web socket URL '$url'")
            val clientEndPoint = WebsocketClientEndpoint().run(url = url)

            clientEndPoint.addMessageHandler(object : WebsocketClientEndpoint.MessageHandler {
                override fun handleMessage(message: String?) {
                    logger.info("Incoming websocket message | $message")
                }
            })
        } catch (exception: InterruptedException) {
            logger.error("Interrupted exception | ${exception.message}")
        } catch (exception: URISyntaxException) {
            logger.error("URI syntax exception | ${exception.message}")
        } catch (exception: Exception) {
            logger.error("Error while handling websocket connection | ${exception.message}")
        }
    }
}