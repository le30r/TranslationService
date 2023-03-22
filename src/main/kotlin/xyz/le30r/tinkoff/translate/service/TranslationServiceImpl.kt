package xyz.le30r.tinkoff.translate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import xyz.le30r.tinkoff.translate.dto.TranslateRequestDto
import xyz.le30r.tinkoff.translate.dto.TranslateResponseDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexRequestDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexResponseDto
import java.net.URI

@Service
class TranslationServiceImpl : TranslationService {

    val restClient: RestTemplate = RestTemplate()


    override fun translate(input: TranslateRequestDto): TranslateResponseDto {
        val headers = HttpHeaders()
        val request = YandexRequestDto("H[fj[aoishgfi0s", "Hello")
        headers.setBearerAuth("authToken")
        restClient.postForEntity(URI("ya.ru"), HttpEntity<YandexRequestDto>(request, headers), YandexResponseDto::class.java)
        TODO("NIE")
    }
}