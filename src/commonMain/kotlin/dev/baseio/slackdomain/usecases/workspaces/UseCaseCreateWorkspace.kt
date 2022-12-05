package dev.baseio.slackdomain.usecases.workspaces

import dev.baseio.slackdomain.AUTH_TOKEN
import dev.baseio.slackdomain.LOGGED_IN_USER
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import dev.baseio.slackdomain.CoroutineDispatcherProvider
import dev.baseio.slackdomain.datasources.local.SKLocalKeyValueSource
import dev.baseio.slackdomain.datasources.remote.auth.SKAuthNetworkDataSource
import dev.baseio.slackdomain.datasources.remote.workspaces.SKNetworkSourceWorkspaces
import kotlinx.coroutines.withContext

class UseCaseCreateWorkspace(
    private val workspaceSource: SKNetworkSourceWorkspaces,
    private val SKAuthNetworkDataSource: SKAuthNetworkDataSource,
    private val skKeyValueData: SKLocalKeyValueSource,
) {
    suspend operator fun invoke(email: String, password: String, domain: String) {
        val result = workspaceSource.saveWorkspace(email, password, domain)
        skKeyValueData.save(AUTH_TOKEN, result.token)
        val user = SKAuthNetworkDataSource.getLoggedInUser().getOrThrow()
        val json = Json.encodeToString(user)
        skKeyValueData.save(LOGGED_IN_USER, json)
    }
}