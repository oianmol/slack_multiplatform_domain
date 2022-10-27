package dev.baseio.slackdomain.usecases.auth

import dev.baseio.slackdomain.AUTH_TOKEN
import dev.baseio.slackdomain.LOGGED_IN_USER
import dev.baseio.slackdomain.datasources.local.SKLocalDatabaseSource
import dev.baseio.slackdomain.datasources.local.SKLocalKeyValueSource
import dev.baseio.slackdomain.datasources.remote.auth.SKAuthNetworkDataSource
import dev.baseio.slackdomain.model.users.DomainLayerUsers
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UseCaseQRAuthUser(
    private val SKAuthNetworkDataSource: SKAuthNetworkDataSource,
    private val skKeyValueData: SKLocalKeyValueSource,
    private val skLocalDatabaseSource: SKLocalDatabaseSource
) {
    suspend operator fun invoke(result: DomainLayerUsers.SKAuthResult) {
        skKeyValueData.save(AUTH_TOKEN, result.token)
        val user = SKAuthNetworkDataSource.getLoggedInUser().getOrThrow()
        val json = Json.encodeToString(user)
        skKeyValueData.save(LOGGED_IN_USER, json)
        skLocalDatabaseSource.clear()
    }
}