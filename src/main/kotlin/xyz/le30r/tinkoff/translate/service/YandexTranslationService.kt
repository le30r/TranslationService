package xyz.le30r.tinkoff.translate.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import xyz.le30r.tinkoff.translate.dto.TranslateRequestDto
import xyz.le30r.tinkoff.translate.dto.TranslateResponseDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexRequestDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexResponseDto
import java.net.URI

@Service
class YandexTranslationService : TranslationService {

    var restClient: RestTemplate = RestTemplate()

    @Value("\${yandex.translate.apikey}")
    lateinit var apiKey: String

    @Value("\${yandex.translate.uri}")
    lateinit var uri: String

    override fun translate(input: TranslateRequestDto): TranslateResponseDto {
        val headers = HttpHeaders()
        val request: YandexRequestDto
        with(input) {
            request = YandexRequestDto(
                data.split(Regex("\\s+")).toTypedArray(),
                params.targetLang,
                params.sourceLang
            )
        }
        headers.add("Authorization", "Api-Key $apiKey")
        val yandexResponse = restClient.postForEntity(
            URI(uri),
            HttpEntity<YandexRequestDto>(request, headers),
            YandexResponseDto::class.java
        )
        val body = yandexResponse.body ?: throw HttpClientErrorException(yandexResponse.statusCode);

        return TranslateResponseDto(body.translations.joinToString(" ") { it.text })
    }
}