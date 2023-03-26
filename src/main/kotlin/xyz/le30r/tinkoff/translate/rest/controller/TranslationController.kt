package xyz.le30r.tinkoff.translate.rest.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import xyz.le30r.tinkoff.translate.dto.TranslationRequestDto
import xyz.le30r.tinkoff.translate.dto.TranslationResponseDto
import xyz.le30r.tinkoff.translate.service.TranslationService

@RestController
class TranslationController {

    @Autowired
    lateinit var translationService: TranslationService;

    @PostMapping("/", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun translate(
        @RequestBody requestDto: TranslationRequestDto, httpServletRequest: HttpServletRequest
    ): TranslationResponseDto {
        val ipAddress = httpServletRequest.remoteAddr
        return translationService.translate(requestDto, ipAddress)
    }
}
