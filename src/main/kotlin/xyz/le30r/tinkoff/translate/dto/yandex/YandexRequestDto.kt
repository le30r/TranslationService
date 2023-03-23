package xyz.le30r.tinkoff.translate.dto.yandex

data class YandexRequestDto(
    var texts: Array<String>,
    val targetLanguageCode: String,
    val sourceLanguageCode: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as YandexRequestDto

        if (!texts.contentEquals(other.texts)) return false
        if (targetLanguageCode != other.targetLanguageCode) return false
        if (sourceLanguageCode != other.sourceLanguageCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = texts.contentHashCode()
        result = 31 * result + targetLanguageCode.hashCode()
        result = 31 * result + (sourceLanguageCode?.hashCode() ?: 0)
        return result
    }
}
