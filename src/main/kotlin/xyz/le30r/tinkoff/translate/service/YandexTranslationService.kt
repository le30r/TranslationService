package xyz.le30r.tinkoff.translate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.le30r.tinkoff.translate.dto.TranslateRequestDto
import xyz.le30r.tinkoff.translate.dto.TranslateResponseDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexRequestDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexResponseDto
import xyz.le30r.tinkoff.translate.service.client.ApiClient
import java.util.Arrays

@Service
class YandexTranslationService : TranslationService {

    @Autowired
    lateinit var client: ApiClient<YandexRequestDto, YandexResponseDto>

    override fun translate(input: TranslateRequestDto): TranslateResponseDto {
        val words = getWords(input)
        val result = arrayOfNulls<String>(words.size)
        words.forEachIndexed { i, e ->
            val request: YandexRequestDto = with(input) {
                YandexRequestDto(
                    arrayOf(e),
                    params.targetLang,
                    params.sourceLang
                )
            }
            val body = client.executePostRequest(request)
            result[i] = body.translations[0].text
        }


        return TranslateResponseDto(result.joinToString(" "))
    }

    private fun getWords(input: TranslateRequestDto): Array<String> {
       return input.data.split(Regex("\\s+")).toTypedArray()
    }
}
