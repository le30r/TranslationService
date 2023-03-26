package xyz.le30r.tinkoff.translate.model

data class TranslationParameter(
    var requestId: Long,
    val parameterName: String,
    val parameterValue: String
)
