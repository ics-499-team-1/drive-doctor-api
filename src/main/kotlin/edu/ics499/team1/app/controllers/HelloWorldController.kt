package edu.ics499.team1.app.controllers

import edu.ics499.team1.app.domains.HelloWorld
import edu.ics499.team1.app.services.HelloWorldService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/hello-world")
class HelloWorldController(
        private val helloWorldService: HelloWorldService
) {

    @GetMapping
    fun getHelloWorld(@RequestParam message: String): HelloWorld {
        return helloWorldService.displayMessage(message)
    }
}
