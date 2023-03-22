package xyz.le30r.tinkoff.translate.rest.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import xyz.le30r.tinkoff.translate.dto.TranslateRequestDto
import xyz.le30r.tinkoff.translate.service.TranslationService

@RestController
class TranslationController {

    @Autowired
    lateinit var translationService: TranslationService;

    @PostMapping("/")
    fun translate(@RequestBody requestDto: TranslateRequestDto): String {
        translationService.translate(requestDto)
        return "Ok"
    }
}