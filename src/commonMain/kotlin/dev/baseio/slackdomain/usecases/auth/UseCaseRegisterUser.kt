package dev.baseio.slackdomain.usecases.auth

import dev.baseio.slackdomain.AUTH_TOKEN
import dev.baseio.slackdomain.LOGGED_IN_USER
import dev.baseio.slackdomain.datasources.local.SKLocalKeyValueSource
import dev.baseio.slackdomain.datasources.remote.auth.SKAuthNetworkDataSource
import dev.baseio.slackdomain.model.users.DomainLayerUsers
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UseCaseRegisterUser(
  private val SKAuthNetworkDataSource: SKAuthNetworkDataSource,
  private val skKeyValueData: SKLocalKeyValueSource
) {
  suspend operator fun invoke(email: String, password: String, workspaceId: String) {
    SKAuthNetworkDataSource.register(
      DomainLayerUsers.SkAuthUser(
        email,
        password,
        user = DomainLayerUsers.SKUser(workspaceId = workspaceId)
      )
    ).apply {
      this.getOrNull()?.let {
        skKeyValueData.save(AUTH_TOKEN, it.token)
        val user = SKAuthNetworkDataSource.getLoggedInUser().getOrThrow()
        val json = Json.encodeToString(user)
        skKeyValueData.save(LOGGED_IN_USER, json)
      }
    }
   /* val result = grpcCalls.register(kmSKAuthUser {
      this.email = email
      this.password = password
      this.user = kmSKUser {
        this.workspaceId = workspaceId
      }
    })
*/


  }
}