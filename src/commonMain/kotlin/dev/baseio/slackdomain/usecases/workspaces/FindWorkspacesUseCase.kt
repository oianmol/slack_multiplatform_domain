package dev.baseio.slackdomain.usecases.workspaces

import dev.baseio.slackdomain.datasources.remote.workspaces.SKNetworkDataSourceReadWorkspaces
import dev.baseio.slackdomain.model.workspaces.DomainLayerWorkspaces

typealias Email = String
typealias Name = String

class FindWorkspacesUseCase(private val SKNetworkDataSourceReadWorkspaces: SKNetworkDataSourceReadWorkspaces) {
  suspend fun byEmail(email: Email): List<DomainLayerWorkspaces.SKWorkspace> {
    return SKNetworkDataSourceReadWorkspaces.findWorkspacesForEmail(email)
  }

  suspend fun byName(name: Name): DomainLayerWorkspaces.SKWorkspace {
    return SKNetworkDataSourceReadWorkspaces.findWorkspaceByName(name)
  }
}