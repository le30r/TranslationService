package xyz.le30r.tinkoff.translate.model

data class TranslationWord(
    val id: Long,
    val word: String,
    val translation: String,
    val requestId: Long
)
