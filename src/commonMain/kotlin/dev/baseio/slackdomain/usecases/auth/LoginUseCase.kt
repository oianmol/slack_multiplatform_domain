package dev.baseio.slackdomain.usecases.auth

import dev.baseio.slackdomain.AUTH_TOKEN
import dev.baseio.slackdomain.LOGGED_IN_USER
import dev.baseio.slackdomain.datasources.local.SKLocalDatabaseSource
import dev.baseio.slackdomain.datasources.local.SKLocalKeyValueSource
import dev.baseio.slackdomain.datasources.remote.auth.SKAuthNetworkDataSource
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LoginUseCase(
  private val SKAuthNetworkDataSource: SKAuthNetworkDataSource,
  private val skKeyValueData: SKLocalKeyValueSource,
  private val skLocalDatabaseSource: SKLocalDatabaseSource
) {
  suspend operator fun invoke(email: String, password: String, workspaceId: String) {
    val result = SKAuthNetworkDataSource.login(email, password, workspaceId).getOrThrow()
    skKeyValueData.save(AUTH_TOKEN, result.token)
    val user = SKAuthNetworkDataSource.getLoggedInUser().getOrThrow()
    val json = Json.encodeToString(user)
    skKeyValueData.save(LOGGED_IN_USER, json)
    skLocalDatabaseSource.clear()
  }
}
