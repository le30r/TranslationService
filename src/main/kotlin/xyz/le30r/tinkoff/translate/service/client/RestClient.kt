package xyz.le30r.tinkoff.translate.service.client

interface RestClient<R, T> {
    fun executePostRequest(requestBody: R): T
}