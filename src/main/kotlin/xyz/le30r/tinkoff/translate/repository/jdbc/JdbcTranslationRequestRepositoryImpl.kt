package xyz.le30r.tinkoff.translate.repository.jdbc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import xyz.le30r.tinkoff.translate.mapper.TranslationRequestMapper
import xyz.le30r.tinkoff.translate.model.TranslationRequest
import xyz.le30r.tinkoff.translate.repository.TranslationRequestRepository
import java.sql.SQLException
import java.sql.Statement
import javax.sql.DataSource

@Repository
class JdbcTranslationRequestRepositoryImpl(@Autowired var dataSource: DataSource) : TranslationRequestRepository {

    val SELECT_ALL_REQUESTS = """SELECT * FROM REQUEST"""
    val INSERT_INTO_REQUEST = """INSERT INTO REQUEST (input, output, timestamp, ip_address)
                                VALUES (?, ?, ?, ?)"""

    override fun save(entity: TranslationRequest): Long =
        dataSource.connection.use {
            val preparedStatement = it.prepareStatement(INSERT_INTO_REQUEST, Statement.RETURN_GENERATED_KEYS)
            with(preparedStatement) {
                setString(1, entity.input)
                setString(2, entity.output)
                setLong(3, entity.timestamp)
                setString(4, entity.ipAddress)
            }

            var id: Long
            if (preparedStatement.executeUpdate() > 0) {
                val rs = preparedStatement.generatedKeys
                rs.next()
                id = rs.getLong(1)
            } else throw SQLException("Insertion failed")

            id
        }


    override fun findAll(): Set<TranslationRequest> {
        val mapper = TranslationRequestMapper()
        dataSource.connection.use {
            val preparedStatement = it.prepareStatement(SELECT_ALL_REQUESTS)
            val rs = preparedStatement.executeQuery()
            return mapper.mapSet(rs)
        }
    }

}