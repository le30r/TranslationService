package xyz.le30r.tinkoff.translate.model

data class WordTranslation(
    val id: Long,
    val word: String,
    val translation: String,
    val requestId: Long
)
