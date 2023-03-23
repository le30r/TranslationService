package xyz.le30r.tinkoff.translate.model

data class TranslationRequest(
    val id: Long,
    val input: String,
    val output: String,
    val timestamp: Long,
    val parameters: Map<String, String>,
    val ipAddress: String
)
