package xyz.le30r.tinkoff.translate.configuration

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.http.HttpHeaders
import org.springframework.web.client.RestTemplate
@Configuration
class TranslateConfiguration {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun () = RestTemplateBuilder().rootUri()
}