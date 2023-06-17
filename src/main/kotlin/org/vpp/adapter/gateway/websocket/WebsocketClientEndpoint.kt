package org.vpp.adapter.gateway.websocket

import java.net.URI
import java.nio.ByteBuffer
import javax.websocket.*

@ClientEndpoint
class WebsocketClientEndpoint {

    private var userSession: Session? = null
    private var messageHandler: MessageHandler? = null

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
        println("opening websocket")
        this.userSession = userSession
    }

    @OnClose
    fun onClose(userSession: Session?, reason: CloseReason?) {
        println("closing websocket")
        this.userSession = null
    }

    @OnMessage
    fun onMessage(message: String?) {
        if (messageHandler != null) {
            messageHandler!!.handleMessage(message)
        }
    }

    @OnMessage
    fun onMessage(bytes: ByteBuffer?) {
        println("Handle byte buffer")
    }

    fun addMessageHandler(msgHandler: MessageHandler?) {
        messageHandler = msgHandler
    }

    interface MessageHandler {
        fun handleMessage(message: String?)
    }
}
