package xyz.le30r.tinkoff.translate.repository

import xyz.le30r.tinkoff.translate.model.TranslationParameter

interface TranslationParameterRepository {
    fun save(entity: TranslationParameter): Long
    fun findAll(): Set<TranslationParameter>
}