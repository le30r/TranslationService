package xyz.le30r.tinkoff.translate.repository

import xyz.le30r.tinkoff.translate.model.TranslationRequest

interface TranslationRequestRepository {
    fun save(entity: TranslationRequest): Long
    fun findAll() : Set<TranslationRequest>
}