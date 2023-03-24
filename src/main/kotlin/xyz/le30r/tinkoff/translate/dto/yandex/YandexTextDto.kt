package xyz.le30r.tinkoff.translate.dto.yandex

data class YandexTextDto (
    val text: String,
    val detectedLanguageCode: String? = null
)
