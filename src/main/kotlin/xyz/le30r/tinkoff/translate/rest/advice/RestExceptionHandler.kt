package xyz.le30r.tinkoff.translate.rest.advice


import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(Exception::class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    protected fun handleUnknownException(
        ex: Exception
    ) = ResponseEntity<String>("Internal server error", INTERNAL_SERVER_ERROR)
}
