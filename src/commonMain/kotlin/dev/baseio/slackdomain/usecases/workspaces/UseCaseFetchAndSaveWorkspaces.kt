package dev.baseio.slackdomain.usecases.workspaces

import dev.baseio.slackdomain.datasources.local.workspaces.SKLocalDataSourceWriteWorkspaces
import dev.baseio.slackdomain.datasources.remote.workspaces.SKNetworkDataSourceReadWorkspaces

class UseCaseFetchAndSaveWorkspaces(
    private val skNetworkDataSourceReadWorkspaces: SKNetworkDataSourceReadWorkspaces,
    private val skLocalDataSourceWriteWorkspaces: SKLocalDataSourceWriteWorkspaces,
) {
    suspend operator fun invoke() {
        kotlin.runCatching {
            val kmSKWorkspaces = skNetworkDataSourceReadWorkspaces.getWorkspaces()
            skLocalDataSourceWriteWorkspaces.saveWorkspaces(kmSKWorkspaces)
        }
    }
}

/*fun KMSKWorkspace.toSKWorkspace(): DomainLayerWorkspaces.SKWorkspace {
    return DomainLayerWorkspaces.SKWorkspace(this.uuid, this.name, this.domain, this.picUrl, this.modifiedTime)
}*/
