package xyz.le30r.tinkoff.translate.dto.yandex

data class YandexResponseDto(
    val translations: Array<YandexTextDto>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as YandexResponseDto

        if (!translations.contentEquals(other.translations)) return false

        return true
    }

    override fun hashCode(): Int {
        return translations.contentHashCode()
    }
}
