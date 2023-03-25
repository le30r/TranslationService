package xyz.le30r.tinkoff.translate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.le30r.tinkoff.translate.dto.TranslateRequestDto
import xyz.le30r.tinkoff.translate.dto.TranslateResponseDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexRequestDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexResponseDto
import xyz.le30r.tinkoff.translate.service.client.RestClient

@Service
class YandexTranslationService : TranslationService {

    @Autowired
    lateinit var client: RestClient<YandexRequestDto, YandexResponseDto>

    override fun translate(input: TranslateRequestDto): TranslateResponseDto {
        val request: YandexRequestDto
        with(input) {
            request = YandexRequestDto(
                data.split(Regex("\\s+")).toTypedArray(),
                params.targetLang,
                params.sourceLang
            )
        }
        val body = client.executePostRequest(request)
        return TranslateResponseDto(body.translations.joinToString(" ") { it.text })
    }
}