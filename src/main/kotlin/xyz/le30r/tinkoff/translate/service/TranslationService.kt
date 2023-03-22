package xyz.le30r.tinkoff.translate.service

import xyz.le30r.tinkoff.translate.dto.TranslateRequestDto
import xyz.le30r.tinkoff.translate.dto.TranslateResponseDto

interface TranslationService {
    fun translate(input: TranslateRequestDto): TranslateResponseDto
}