package dev.baseio.slackdomain.datasources.remote.auth

import dev.baseio.slackdomain.model.users.DomainLayerUsers

interface SKAuthNetworkDataSource {
  suspend fun login(email: String, password: String, workspaceId: String): Result<DomainLayerUsers.SKAuthResult>
  suspend fun getLoggedInUser(): Result<DomainLayerUsers.SKUser>
  suspend fun register(skAuthUser: DomainLayerUsers.SkAuthUser): Result<DomainLayerUsers.SKAuthResult>
}