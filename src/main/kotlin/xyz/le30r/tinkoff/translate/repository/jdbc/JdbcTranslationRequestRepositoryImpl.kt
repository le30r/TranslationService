package xyz.le30r.tinkoff.translate.repository.jdbc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import xyz.le30r.tinkoff.translate.model.TranslationRequest
import xyz.le30r.tinkoff.translate.repository.TranslationRequestRepository
import javax.sql.DataSource

@Repository
class JdbcTranslationRequestRepositoryImpl : TranslationRequestRepository {
    @Autowired
    lateinit var dataSource: DataSource

    override fun save(entity: TranslationRequest) {
        TODO("Not yet implemented")
    }

}