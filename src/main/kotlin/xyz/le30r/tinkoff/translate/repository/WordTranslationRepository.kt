package xyz.le30r.tinkoff.translate.repository

import xyz.le30r.tinkoff.translate.model.TranslationWord

interface WordTranslationRepository {
    fun save(entity: TranslationWord)
    fun findAll() : Set<TranslationWord>
}