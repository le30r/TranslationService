package xyz.le30r.tinkoff.translate.repository.jdbc

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.jdbc.Sql
import xyz.le30r.tinkoff.translate.model.TranslationParameter
import java.sql.SQLException
import javax.sql.DataSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class JdbcTranslationParameterRepositoryImplTest {


    private lateinit var repository: JdbcTranslationParameterRepositoryImpl
    private lateinit var dataSource: DataSource
    private lateinit var db: EmbeddedDatabase
    @BeforeAll
    fun setupDataSource() {
        db = EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:schema.sql")
            .build()
        dataSource = db
        repository = JdbcTranslationParameterRepositoryImpl(dataSource)
        insertRequestData()
    }

    @AfterAll
    fun shutdownDatabase() {
        db.shutdown()
    }


    private val testEntity = TranslationParameter(1, "sourceLang", "ru")
    private val testEntity1 = TranslationParameter(2, "sourceLang", "ru")

    @Test
    fun save() {
        repository.save(testEntity)
        assert(repository.findAll().contains(testEntity))
    }

    @Test
    fun saveTwice() {
        repository.save(testEntity1)
        assertThrows(SQLException::class.java) {
            repository.save(testEntity1)
        }
    }

    private fun insertRequestData() {
        dataSource.connection.use {
            it.prepareStatement( """INSERT INTO REQUEST (id, input, output, timestamp, ip_address)
                                VALUES (1, '', '', 0, '')""").executeUpdate()
            it.prepareStatement( """INSERT INTO REQUEST (id, input, output, timestamp, ip_address)
                                VALUES (2, '', '', 0, '')""").executeUpdate()
        }
    }
}