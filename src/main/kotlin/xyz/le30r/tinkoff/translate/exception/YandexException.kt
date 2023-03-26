package xyz.le30r.tinkoff.translate.exception

import org.springframework.http.HttpStatusCode
import org.springframework.web.client.HttpClientErrorException

class YandexException(code: HttpStatusCode, msg: String) : Exception() {
    val statusCode = code
    override val message = msg
}