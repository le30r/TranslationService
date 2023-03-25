package xyz.le30r.tinkoff.translate.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import xyz.le30r.tinkoff.translate.dto.TranslateParamsDto
import xyz.le30r.tinkoff.translate.dto.TranslateRequestDto
import xyz.le30r.tinkoff.translate.dto.TranslateResponseDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexRequestDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexResponseDto
import xyz.le30r.tinkoff.translate.dto.yandex.YandexTextDto
import xyz.le30r.tinkoff.translate.service.client.YandexApiClient

@ExtendWith(MockitoExtension::class)
internal class YandexTranslationServiceTest {
    //Hack to make any() in Kotlin correct
    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

    @Mock
    lateinit var yandexApiClient: YandexApiClient

    @InjectMocks
    lateinit var service: YandexTranslationService

    @BeforeEach
    fun setUp() {
        Mockito.`when`(yandexApiClient.executePostRequest(any(YandexRequestDto::class.java))).thenAnswer {
            val arg = it.arguments[0] as YandexRequestDto
            val result = arrayOfNulls<String>(arg.texts.size)
            arg.texts.forEachIndexed { i, e ->
                result[i] = translationDictionary[e]
            }
            return@thenAnswer YandexResponseDto(result.map { s -> YandexTextDto(s ?: "")}.toTypedArray())
        }
    }

    @Test
    fun translateSimpleWord() {
        val expectResponse = TranslateResponseDto("тест")
        val request = TranslateRequestDto("test", TranslateParamsDto("en", "ru"))
        val response = service.translate(request)

        assertEquals(response, expectResponse)
    }

    @Test
    fun translatePhrase() {
        val expectResponse = TranslateResponseDto("привет мир")
        val request = TranslateRequestDto("hello world", TranslateParamsDto("en", "ru"))
        val response = service.translate(request)

        assertEquals(response, expectResponse)
    }



    private val translationDictionary = mapOf<String, String>(
        "hello" to "привет",
        "world" to "мир",
        "test" to "тест"
    )
}