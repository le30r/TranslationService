package xyz.le30r.tinkoff.translate.mapper

import xyz.le30r.tinkoff.translate.model.TranslationParameter
import xyz.le30r.tinkoff.translate.model.TranslationWord
import java.sql.ResultSet
import java.sql.SQLException

private const val ID_COLUMN = "id"
private const val REQUEST_ID_COLUMN = "request_id"
private const val WORD_COLUMN = "word"
private const val TRANSLATION_COLUMN = "translation"

class TranslationWordSetMapper : SetMapper<TranslationWord> {
    override fun mapSet(rs: ResultSet): Set<TranslationWord> {
        val result = mutableSetOf<TranslationWord>()
        try {
            while (rs.next()) {
                result.add(mapRow(rs))
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return result
    }

    private fun mapRow(rs: ResultSet): TranslationWord {
        val id = rs.getLong(ID_COLUMN)
        val requestId = rs.getLong(REQUEST_ID_COLUMN)
        val word = rs.getString(WORD_COLUMN)
        val translation = rs.getString(TRANSLATION_COLUMN)
        return TranslationWord(id, word, translation, requestId)
    }
}
