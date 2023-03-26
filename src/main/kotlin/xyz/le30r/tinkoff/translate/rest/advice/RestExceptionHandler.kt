package xyz.le30r.tinkoff.translate.rest.advice


import jakarta.servlet.ServletException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.ErrorResponse
import org.springframework.web.HttpMediaTypeException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import xyz.le30r.tinkoff.translate.exception.YandexException
import java.util.concurrent.ExecutionException

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(BAD_REQUEST)
    protected fun handleInputErrorException(
        ex: Exception
    ) = ResponseEntity<String>("400 Bad request. Check your input", BAD_REQUEST)


    @ExceptionHandler(ExecutionException::class)
    protected fun handleYExecutionExceptionException(
        ex: ExecutionException
    ): Any {
        when (ex.cause) {
            is YandexException -> {
                return handleYandexException(ex)
            }
            else -> throw IllegalStateException()
        }
    }
    @ExceptionHandler(ServletException::class)
    protected fun handleHttpException(
        ex: ErrorResponse
    ) = ResponseEntity<String>(ex.body.title, ex.statusCode)

    @ExceptionHandler(Exception::class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    protected fun handleUnknownException(
        ex: Exception
    ) = ResponseEntity<String>("500 Internal server error", INTERNAL_SERVER_ERROR)

    private fun handleYandexException(ex: ExecutionException): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val cause = (ex.cause as YandexException)
        return ResponseEntity<String>(
            (cause.message.replace("<EOL>", "\n")), headers,
            cause.statusCode
        )
    }

}
