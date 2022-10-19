package dev.baseio.slackdomain.datasources.remote.workspaces

import dev.baseio.slackdomain.model.workspaces.DomainLayerWorkspaces
import dev.baseio.slackdomain.usecases.workspaces.Email
import dev.baseio.slackdomain.usecases.workspaces.Name

interface SKNetworkDataSourceReadWorkspaces {
  suspend fun findWorkspacesForEmail(email: Email): List<DomainLayerWorkspaces.SKWorkspace>
  suspend fun findWorkspaceByName(name: Name): DomainLayerWorkspaces.SKWorkspace
  suspend fun getWorkspaces(): List<DomainLayerWorkspaces.SKWorkspace>
}