package xyz.le30r.tinkoff.translate.mapper.set

import java.sql.ResultSet

interface SetMapper<T> {
    fun mapSet(rs: ResultSet): Set<T>
}