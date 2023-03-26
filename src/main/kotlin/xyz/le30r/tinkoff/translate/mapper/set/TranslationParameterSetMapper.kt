package xyz.le30r.tinkoff.translate.mapper.set

import xyz.le30r.tinkoff.translate.model.TranslationParameter
import java.sql.ResultSet
import java.sql.SQLException

private const val REQUEST_ID_COLUMN = "request_id"
private const val PARAMETER_NAME_COLUMN = "parameter_name"
private const val PARAMETER_VALUE_COLUMN = "parameter_value"

class TranslationParameterSetMapper : SetMapper<TranslationParameter> {

    override fun mapSet(rs: ResultSet): Set<TranslationParameter> {
        val result = mutableSetOf<TranslationParameter>()
        try {
            while (rs.next()) {
                result.add(mapRow(rs))
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return result
    }

    private fun mapRow(rs: ResultSet): TranslationParameter {
        val requestId = rs.getLong(REQUEST_ID_COLUMN)
        val paramName = rs.getString(PARAMETER_NAME_COLUMN)
        val paramValue = rs.getString(PARAMETER_VALUE_COLUMN)
        return TranslationParameter(requestId, paramName, paramValue)
    }
}