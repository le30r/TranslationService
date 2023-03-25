package xyz.le30r.tinkoff.translate.model

data class TranslationRequest(
    val id: Long,
    val input: String,
    val output: String,
    val timestamp: Long,
    val ipAddress: String
)
