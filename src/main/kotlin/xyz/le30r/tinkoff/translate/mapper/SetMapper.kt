package xyz.le30r.tinkoff.translate.mapper

import java.sql.ResultSet

interface SetMapper<T> {
    fun mapSet(rs: ResultSet): Set<T>
}