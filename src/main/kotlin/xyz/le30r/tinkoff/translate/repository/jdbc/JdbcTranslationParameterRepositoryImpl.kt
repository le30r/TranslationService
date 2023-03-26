package xyz.le30r.tinkoff.translate.repository.jdbc

import xyz.le30r.tinkoff.translate.mapper.TranslationParameterSetMapper
import xyz.le30r.tinkoff.translate.model.TranslationParameter
import xyz.le30r.tinkoff.translate.repository.TranslationParameterRepository
import javax.sql.DataSource
private const val SELECT_ALL_PARAMETERS = """SELECT * FROM PARAMETER"""
private const val INSERT_INTO_PARAMETER = """INSERT INTO PARAMETER (request_id, parameter_name, parameter_value)
                                VALUES (?, ?, ?)"""
class JdbcTranslationParameterRepositoryImpl(private val dataSource: DataSource) : TranslationParameterRepository {

    override fun save(entity: TranslationParameter) {
        dataSource.connection.use {
            val preparedStatement = it.prepareStatement(INSERT_INTO_PARAMETER)
            with(preparedStatement) {
                setLong(1, entity.requestId)
                setString(2, entity.parameterName)
                setString(3, entity.parameterValue)
            }
            preparedStatement.executeUpdate()
        }
    }

    override fun findAll(): Set<TranslationParameter> {
        val mapper = TranslationParameterSetMapper()
        dataSource.connection.use {
            val preparedStatement = it.prepareStatement(SELECT_ALL_PARAMETERS)
            val rs = preparedStatement.executeQuery()
            return mapper.mapSet(rs)
        }
    }
}