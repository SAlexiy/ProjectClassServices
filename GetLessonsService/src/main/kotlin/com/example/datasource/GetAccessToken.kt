package com.example.datasource

import com.example.datamodel.token.AccessToken
import com.example.consts.Consts
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import java.util.logging.Logger




class GetAccessToken {

    private val client: HttpClient = HttpClient(CIO){
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    private val url = Consts.accessTokenUrl
    private val log = Logger.getLogger("accessToken")


    fun getToken(): String = runBlocking{

        log.info("fun getToken")

        return@runBlocking Json.decodeFromJsonElement<AccessToken>(
            Json.parseToJsonElement(
                client.post(url).bodyAsText()
            )).accessToken
    }
}