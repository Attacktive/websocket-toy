package com.github.attacktive.websockettoy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebsocketToyApplication

fun main(args: Array<String>) {
	runApplication<WebsocketToyApplication>(*args)
}
