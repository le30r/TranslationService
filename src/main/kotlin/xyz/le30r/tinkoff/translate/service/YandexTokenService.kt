package xyz.le30r.tinkoff.translate.service

import org.springframework.scheduling.annotation.Scheduled
import xyz.le30r.tinkoff.translate.entity.Token
import xyz.le30r.tinkoff.translate.entity.YandexToken


class YandexTokenService : TokenService {

    val token = YandexToken("")

    override fun getToken(): YandexToken {
        return token
    }

    @Scheduled()
    fun refreshToken() {

    }
}