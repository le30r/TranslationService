package xyz.le30r.tinkoff.translate.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TranslationController {
    @GetMapping("/")
    fun translate(): String {
        return "Ok";
    }
}