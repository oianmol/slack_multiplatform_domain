package dev.baseio.slackdomain.usecases.workspaces

import dev.baseio.slackdomain.datasources.remote.workspaces.SKNetworkSourceWorkspaces

class UseCaseAuthWorkspace(
    private val workspaceSource: SKNetworkSourceWorkspaces,
) {
    suspend operator fun invoke(email: String, domain: String) = workspaceSource.sendMagicLink(email, domain)
}