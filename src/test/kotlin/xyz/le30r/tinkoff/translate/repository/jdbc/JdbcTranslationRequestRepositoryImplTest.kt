package xyz.le30r.tinkoff.translate.repository.jdbc

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import xyz.le30r.tinkoff.translate.model.TranslationRequest
import javax.sql.DataSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class JdbcTranslationRequestRepositoryImplTest {

    private lateinit var repository: JdbcTranslationRequestRepositoryImpl
    private lateinit var dataSource: DataSource

    private lateinit var db: EmbeddedDatabase
    @BeforeAll
    fun setupDataSource() {
        db = EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:schema.sql")
            .build()
        dataSource = db
        repository = JdbcTranslationRequestRepositoryImpl(dataSource)
    }

    @AfterAll
    fun shutdownDatabase() {
        db.shutdown()
    }

    private val testEntity = TranslationRequest(
        1,
        "hello, world!",
        "привет, мир",
        121313216,
        "0.0.0.1:8080"
    )

    @Test
    fun save() {
        repository.save(testEntity)
        assert(repository.findAll().contains(testEntity))
    }
}
