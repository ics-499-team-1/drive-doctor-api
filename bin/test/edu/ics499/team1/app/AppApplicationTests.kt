package edu.ics499.team1.app

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "spring.datasource.url=jdb"
    ]
)
class AppApplicationTests {

    @Test
    fun contextLoads() {
    }

}
