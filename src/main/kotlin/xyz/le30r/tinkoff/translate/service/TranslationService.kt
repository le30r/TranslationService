package xyz.le30r.tinkoff.translate.service

import xyz.le30r.tinkoff.translate.dto.TranslationRequestDto
import xyz.le30r.tinkoff.translate.dto.TranslationResponseDto

interface TranslationService {
    fun translate(input: TranslationRequestDto, ipAddress: String): TranslationResponseDto
}