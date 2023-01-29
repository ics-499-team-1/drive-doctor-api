package edu.ics499.team1.app.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/hello-world")
class HelloWorldController {

    @GetMapping
    fun getHelloWorld() = "Hello World"
}
