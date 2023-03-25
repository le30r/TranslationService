package xyz.le30r.tinkoff.translate.repository.jdbc

import xyz.le30r.tinkoff.translate.model.TranslationParameter
import xyz.le30r.tinkoff.translate.repository.TranslationParameterRepository

class JdbcTranslationParameterRepositoryImpl : TranslationParameterRepository {
    override fun save(entity: TranslationParameter): Long {
        TODO("Not yet implemented")
    }

    override fun findAll(): Set<TranslationParameter> {
        TODO("Not yet implemented")
    }
}