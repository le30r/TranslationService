package xyz.le30r.tinkoff.translate.dto.yandex

data class YandexResponseDto(
    val text: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as YandexResponseDto

        if (!text.contentEquals(other.text)) return false

        return true
    }

    override fun hashCode(): Int {
        return text.contentHashCode()
    }
}
