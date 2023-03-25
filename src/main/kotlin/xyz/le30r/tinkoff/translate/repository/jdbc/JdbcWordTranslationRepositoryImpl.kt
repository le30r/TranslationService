package xyz.le30r.tinkoff.translate.repository.jdbc

import xyz.le30r.tinkoff.translate.model.WordTranslation
import xyz.le30r.tinkoff.translate.repository.WordTranslationRepository

class JdbcWordTranslationRepositoryImpl : WordTranslationRepository {
    override fun save(entity: WordTranslation): Long {
        TODO("Not yet implemented")
    }

    override fun findAll(): Set<WordTranslation> {
        TODO("Not yet implemented")
    }

}