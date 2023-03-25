package xyz.le30r.tinkoff.translate.repository

import xyz.le30r.tinkoff.translate.model.WordTranslation

interface WordTranslationRepository {
    fun save(entity: WordTranslation): Long
    fun findAll() : Set<WordTranslation>
}