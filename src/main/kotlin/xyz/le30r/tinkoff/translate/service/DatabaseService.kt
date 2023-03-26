package xyz.le30r.tinkoff.translate.service

import xyz.le30r.tinkoff.translate.dto.TranslationRequestDto

interface DatabaseService {
    fun saveRequestInfoInDb(
        requestDto: TranslationRequestDto,
        words: Array<String>,
        ipAddress: String,
        result: Array<String?>
    )
}