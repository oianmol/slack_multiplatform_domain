package dev.baseio.slackdomain.datasources.remote.workspaces

import dev.baseio.slackdomain.model.users.DomainLayerUsers
import dev.baseio.slackdomain.model.workspaces.DomainLayerWorkspaces

interface SKNetworkSourceWorkspaces {
  fun saveWorkspace(email: String, password: String, domain: String): DomainLayerUsers.SKAuthResult
}