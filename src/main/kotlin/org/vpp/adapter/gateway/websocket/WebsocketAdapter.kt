package org.vpp.adapter.gateway.websocket

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.vpp.adapter.gateway.websocket.dto.helper.MessageType
import org.vpp.adapter.gateway.websocket.handler.SystemRegisteredHandler
import org.vpp.adapter.gateway.websocket.handler.SystemStatusChangedHandler
import org.vpp.adapter.gateway.websocket.handler.VoltageMeasurementHandler
import org.vpp.storage.gateway.GatewayEntity
import java.net.URISyntaxException

@Service
class WebsocketAdapter @Autowired constructor(
    private val systemRegisteredHandler: SystemRegisteredHandler,
    private val voltageMeasurementHandler: VoltageMeasurementHandler,
    private val systemStatusChangedHandler: SystemStatusChangedHandler
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun connect(gateway: GatewayEntity) {
        try {
            val url = "ws://${gateway.gatewayHost}:${gateway.websocketPort}/${gateway.gatewayUrl}"
            logger.info("Connecting to web socket URL '$url'")
            val clientEndPoint = WebsocketClientEndpoint().run(url = url)

            clientEndPoint.addMessageHandler(object : WebsocketClientEndpoint.MessageHandler {
                override fun handleMessage(message: String?) {
                    message ?: return
                    logger.info("Incoming websocket message | $message")
                    when {
                        message.contains(MessageType.SystemStatusChanged.name) ->
                            systemStatusChangedHandler.handle(message = message)

                        message.contains(MessageType.SystemRegistered.name) ->
                            systemRegisteredHandler.handle(message = message)

                        message.contains(MessageType.VoltageMeasurement.name) ->
                            voltageMeasurementHandler.handle(message = message)

                        message.contains(MessageType.SystemRegistered.name) -> logger.info("System registered")
                        message == "Connection successful" -> logger.info("Connection successful")
                        else -> logger.warn("Unknown message '$message' received")
                    }
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
