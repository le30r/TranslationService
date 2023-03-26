package xyz.le30r.tinkoff.translate.mapper

interface EntityMapper<T, R> {
    fun map(source: T): R
}
