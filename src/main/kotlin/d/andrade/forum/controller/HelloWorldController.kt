package d.andrade.forum.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/hello")
class HelloWorldController {

    @GetMapping
    fun hello(): String {
        return "Hello World 123!"
    }
}