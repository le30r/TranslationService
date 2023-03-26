package xyz.le30r.tinkoff.translate.service.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import xyz.le30r.tinkoff.translate.dto.yandex.YandexRequestDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexResponseDto
import xyz.le30r.tinkoff.translate.exception.YandexException
import java.net.URI

@Service
class YandexApiClient : ApiClient<YandexRequestDto, YandexResponseDto> {

    var restClient: RestTemplate = RestTemplate()

    @Value("\${yandex.translate.apikey}")
    lateinit var apiKey: String

    @Value("\${yandex.translate.uri}")
    lateinit var uri: String

    override fun executePostRequest(requestBody: YandexRequestDto): YandexResponseDto {
        val headers = HttpHeaders()
        headers.add("Authorization", "Api-Key $apiKey")
        try {
            val request = restClient.postForEntity(
                URI(uri),
                HttpEntity<YandexRequestDto>(requestBody, headers),
                YandexResponseDto::class.java)
            return request.body ?: throw YandexException(HttpStatusCode.valueOf(500), "Serialization exception")

        } catch (ex: HttpClientErrorException) {
            throw YandexException(ex.statusCode, ex.responseBodyAsString ?: "Unknown error")
        }
    }
}
