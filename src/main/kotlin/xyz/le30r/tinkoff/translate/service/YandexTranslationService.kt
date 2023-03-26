package xyz.le30r.tinkoff.translate.service


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.le30r.tinkoff.translate.dto.TranslationRequestDto
import xyz.le30r.tinkoff.translate.dto.TranslationResponseDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexRequestDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexResponseDto
import xyz.le30r.tinkoff.translate.service.client.ApiClient
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.FutureTask
import java.util.concurrent.TimeUnit


const val THREADS_COUNT = 20
@Service
class YandexTranslationService : TranslationService {

    @Autowired
    lateinit var client: ApiClient<YandexRequestDto, YandexResponseDto>

    @Autowired
    lateinit var dbService: DatabaseService

    override fun translate(input: TranslationRequestDto, ipAddress: String): TranslationResponseDto {
        val words = getWords(input)
            val result = translateParallel(words.toList(), input.params.sourceLang, input.params.targetLang)
        dbService.saveRequestInfoInDb(input, words, ipAddress, result.toTypedArray())
        return TranslationResponseDto(result.joinToString(" "))
    }

    private fun translateParallel(words: List<String>, sourceLang: String?, targetLang: String): List<String> {
        val parts = words.chunked(words.size / THREADS_COUNT + 1)

        val executorService = Executors.newFixedThreadPool(THREADS_COUNT)

        val futures = arrayListOf<Future<YandexResponseDto>>()

        for (part in parts) {
            val request = YandexRequestDto(part.toTypedArray(), targetLang,sourceLang)
            futures.add(executorService.submit(Callable {
                client.executePostRequest(request)
            }))
        }
        executorService.shutdown()

        val translatedWords = arrayListOf<String>()
        for (future in futures) {
            val response = future.get()
            translatedWords.addAll(response.translations.map {it.text ?: "" })
        }
        return translatedWords
    }

    private fun getWords(input: TranslationRequestDto): Array<String> {
       return input.data.split(Regex("\\s+")).toTypedArray()
    }
}
