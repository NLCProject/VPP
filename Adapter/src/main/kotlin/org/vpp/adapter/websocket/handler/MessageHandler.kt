package org.vpp.adapter.websocket.handler

interface MessageHandler {

    fun handle(message: String)
}
