package xyz.le30r.tinkoff.translate.repository

import xyz.le30r.tinkoff.translate.model.TranslationParameter

interface TranslationParameterRepository {
    fun save(entity: TranslationParameter)
    fun findAll(): Set<TranslationParameter>
}