package xyz.le30r.tinkoff.translate.service

import xyz.le30r.tinkoff.translate.entity.Token

interface TokenService {
    fun getToken() : Token
}