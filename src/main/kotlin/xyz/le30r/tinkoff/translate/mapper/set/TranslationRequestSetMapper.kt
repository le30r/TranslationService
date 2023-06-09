package xyz.le30r.tinkoff.translate.mapper.set

import xyz.le30r.tinkoff.translate.model.TranslationRequest
import java.sql.ResultSet
import java.sql.SQLException

private const val ID_COLUMN = "id"
private const val INPUT_COLUMN = "input"
private const val OUTPUT_COLUMN = "output"
private const val TIMESTAMP_COLUMN = "timestamp"
private const val IP_COLUMN = "ip_address"

class TranslationRequestSetMapper : SetMapper<TranslationRequest> {

    override fun mapSet(rs: ResultSet): Set<TranslationRequest> {
        val result = mutableSetOf<TranslationRequest>()
        try {
            while (rs.next()) {
                result.add(mapRow(rs))
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return result
    }

    private fun mapRow(rs: ResultSet): TranslationRequest {
        val id = rs.getLong(ID_COLUMN)
        val input = rs.getString(INPUT_COLUMN)
        val output = rs.getString(OUTPUT_COLUMN)
        val timestamp = rs.getLong(TIMESTAMP_COLUMN)
        val ip = rs.getString(IP_COLUMN)
        return TranslationRequest(id, input, output, timestamp, ip)
    }

}
