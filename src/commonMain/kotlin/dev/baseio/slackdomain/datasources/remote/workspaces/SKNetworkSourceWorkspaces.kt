package dev.baseio.slackdomain.datasources.remote.workspaces

import dev.baseio.slackdomain.model.users.DomainLayerUsers
interface SKNetworkSourceWorkspaces {
  suspend fun saveWorkspace(email: String, password: String, domain: String): DomainLayerUsers.SKAuthResult
}