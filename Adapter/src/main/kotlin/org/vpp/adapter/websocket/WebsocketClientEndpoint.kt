package org.vpp.adapter.websocket

import org.slf4j.LoggerFactory
import java.net.URI
import java.nio.ByteBuffer
import javax.websocket.*

@ClientEndpoint
class WebsocketClientEndpoint {

    private var userSession: Session? = null
    private var messageHandler: MessageHandler? = null
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun run(url: String): WebsocketClientEndpoint {
        try {
            val container = ContainerProvider.getWebSocketContainer()
            container.connectToServer(this, URI(url))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

        return this
    }

    @OnOpen
    fun onOpen(userSession: Session?) {
        logger.info("Opening websocket connection")
        this.userSession = userSession
    }

    @OnClose
    fun onClose(userSession: Session?, reason: CloseReason?) {
        logger.info("Closing websocket connection")
        this.userSession = null
    }

    @OnMessage
    fun onMessage(message: String?) {
        if (messageHandler != null) {
            messageHandler!!.handleMessage(message)
        }
    }

    @OnMessage
    fun onMessage(bytes: ByteBuffer?) { }

    fun addMessageHandler(msgHandler: MessageHandler?) {
        messageHandler = msgHandler
    }

    interface MessageHandler {
        fun handleMessage(message: String?)
    }
}
