package xyz.le30r.tinkoff.translate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.le30r.tinkoff.translate.dto.TranslationRequestDto
import xyz.le30r.tinkoff.translate.mapper.TranslationParameterMapper
import xyz.le30r.tinkoff.translate.model.TranslationRequest
import xyz.le30r.tinkoff.translate.model.TranslationWord
import xyz.le30r.tinkoff.translate.repository.TranslationParameterRepository
import xyz.le30r.tinkoff.translate.repository.TranslationRequestRepository
import xyz.le30r.tinkoff.translate.repository.TranslationWordRepository
import java.sql.SQLException
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.sql.DataSource

@Service
class DatabaseServiceImpl : DatabaseService {
    @Autowired
    lateinit var dataSource: DataSource

    @Autowired
    lateinit var requestRepository: TranslationRequestRepository

    @Autowired
    lateinit var wordsRepository: TranslationWordRepository

    @Autowired
    lateinit var parametersRepository: TranslationParameterRepository


    override fun saveRequestInfoInDb(
        requestDto: TranslationRequestDto,
        words: Array<String>,
        ipAddress: String,
        result: Array<String?>
    ) {
        val connection = dataSource.connection
        try {
            connection.autoCommit = false
            val requestId = saveRequest(requestDto, result, ipAddress)
            saveWords(words, result, requestId)
            saveParameters(requestDto, requestId)
        }
        catch (exception: SQLException) {
            connection.rollback()
        }
        finally {
            connection.autoCommit = false
            connection.commit()
        }
    }

    private fun saveParameters(requestDto: TranslationRequestDto, requestId: Long) {
        val params = TranslationParameterMapper().map(requestDto.params)
        params.forEach {
            it.requestId = requestId
            parametersRepository.save(it)
        }
    }

    private fun saveWords(
        words: Array<String>,
        result: Array<String?>,
        requestId: Long
    ) = words.forEachIndexed { i, entity ->
            val dbEntity = TranslationWord(i.toLong(), entity, result[i] ?: "", requestId)
            wordsRepository.save(dbEntity)
        }


    private fun saveRequest(
        requestDto: TranslationRequestDto,
        result: Array<String?>,
        ipAddress: String
    ): Long {
        val request = TranslationRequest(
            0,
            requestDto.data,
            result.joinToString { it ?: "" },
            Timestamp.valueOf(LocalDateTime.now()).time,
            ipAddress
        )
        return requestRepository.save(request)
    }
}