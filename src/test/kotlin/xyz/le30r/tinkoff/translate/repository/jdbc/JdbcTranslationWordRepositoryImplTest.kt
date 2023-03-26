package xyz.le30r.tinkoff.translate.repository.jdbc

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import xyz.le30r.tinkoff.translate.model.TranslationWord
import java.sql.SQLException
import javax.sql.DataSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class JdbcTranslationWordRepositoryImplTest {
    private lateinit var repository: JdbcTranslationWordRepositoryImpl
    private lateinit var dataSource: DataSource

    private lateinit var db: EmbeddedDatabase
    @BeforeAll
    fun setupDataSource() {
        db = EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:schema.sql")
            .build()
        dataSource = db
        repository = JdbcTranslationWordRepositoryImpl(dataSource)
        insertRequestData()
    }

    @AfterAll
    fun shutdownDatabase() {
        db.shutdown()
    }

    private val testEntities = arrayOf(
        TranslationWord(0, "hello", "привет", 1),
        TranslationWord(1, "test", "test", 1))
    private val secondEntity = TranslationWord(0, "hello", "привет", 2)
    @Test
    fun save() {
        for (entity in testEntities) {
            repository.save(entity)
        }
        assert(repository.findAll().containsAll(testEntities.toList()))
    }

    @Test
    fun saveTwice() {
        repository.save(secondEntity)
        assertThrows(SQLException::class.java) {
            repository.save(secondEntity)
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
