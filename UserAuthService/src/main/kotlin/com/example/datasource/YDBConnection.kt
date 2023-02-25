package com.example.userauthservice.datasource

import tech.ydb.core.auth.AuthProvider
import tech.ydb.core.auth.TokenAuthProvider
import tech.ydb.core.grpc.GrpcTransport
import tech.ydb.table.SessionRetryContext
import tech.ydb.table.TableClient

/** устанавливает соединение с YDB*/
class YDBConnection(private val getAccessToken: GetAccessToken) {
    val transport: GrpcTransport
    val tableClient: TableClient
    val database: String
    val retryCtx: SessionRetryContext

    val connectionString = "grpcs://ydb.serverless.yandexcloud.net:2135/ru-central1/b1ggb5a09ctmr64v8iau/etnd5s81uam03l3247op"

    init {
        val authProvider: AuthProvider = TokenAuthProvider(getAccessToken.getToken())

        this.transport = GrpcTransport.forConnectionString(connectionString)
            .withAuthProvider(authProvider).build()

        this.tableClient = TableClient.newClient(transport).build()
        this.database = transport.database
        this.retryCtx = SessionRetryContext.create(tableClient).build()
    }
}
