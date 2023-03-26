package xyz.le30r.tinkoff.translate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.le30r.tinkoff.translate.dto.TranslationRequestDto
import xyz.le30r.tinkoff.translate.dto.TranslationResponseDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexRequestDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexResponseDto
import xyz.le30r.tinkoff.translate.service.client.ApiClient

@Service
class YandexTranslationService : TranslationService {

    @Autowired
    lateinit var client: ApiClient<YandexRequestDto, YandexResponseDto>

    @Autowired
    lateinit var dbService: DatabaseService

    override fun translate(input: TranslationRequestDto, ipAddress: String): TranslationResponseDto {
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

        dbService.saveRequestInfoInDb(input, words, ipAddress, result)

        return TranslationResponseDto(result.joinToString(" "))
    }

    private fun getWords(input: TranslationRequestDto): Array<String> {
       return input.data.split(Regex("\\s+")).toTypedArray()
    }
}
