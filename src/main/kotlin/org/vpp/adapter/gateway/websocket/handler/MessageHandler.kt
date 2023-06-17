package org.vpp.adapter.gateway.websocket.handler

interface MessageHandler {

    fun handle(message: String)
}
