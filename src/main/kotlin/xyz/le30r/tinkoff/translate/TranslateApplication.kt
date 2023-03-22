package xyz.le30r.tinkoff.translate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TranslateApplication

fun main(args: Array<String>) {
    runApplication<TranslateApplication>(*args)
}
