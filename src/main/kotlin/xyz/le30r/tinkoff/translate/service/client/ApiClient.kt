package xyz.le30r.tinkoff.translate.service.client

interface ApiClient<R, T> {
    fun executePostRequest(requestBody: R): T
}
