package xyz.le30r.tinkoff.translate.repository.jdbc

import xyz.le30r.tinkoff.translate.mapper.TranslationWordSetMapper
import xyz.le30r.tinkoff.translate.model.TranslationWord
import xyz.le30r.tinkoff.translate.repository.WordTranslationRepository
import javax.sql.DataSource

private const val SELECT_ALL_WORDS = """SELECT * FROM WORD"""
private const val INSERT_INTO_WORDS = """INSERT INTO WORD (id, request_id, word, translation)
                                VALUES (?, ?, ?, ?)"""
class JdbcTranslationWordRepositoryImpl(private val dataSource: DataSource) : WordTranslationRepository {
    override fun save(entity: TranslationWord) {
        dataSource.connection.use {
            val preparedStatement = it.prepareStatement(INSERT_INTO_WORDS)
            with(preparedStatement) {
                setLong(1, entity.id)
                setLong(2, entity.requestId)
                setString(3, entity.word)
                setString(4, entity.translation)
            }
            preparedStatement.executeUpdate()
        }
    }

    override fun findAll(): Set<TranslationWord> {
        val mapper = TranslationWordSetMapper()
        dataSource.connection.use {
            val preparedStatement = it.prepareStatement(SELECT_ALL_WORDS)
            val rs = preparedStatement.executeQuery()
            return mapper.mapSet(rs)
        }
    }
}
