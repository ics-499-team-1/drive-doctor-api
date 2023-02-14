package edu.ics499.team1.app.services

import edu.ics499.team1.app.domains.HelloWorld
import org.springframework.stereotype.Service

@Service
class HelloWorldService {

    fun displayMessage(message: String): HelloWorld {
        return HelloWorld("hello")
    }
}
