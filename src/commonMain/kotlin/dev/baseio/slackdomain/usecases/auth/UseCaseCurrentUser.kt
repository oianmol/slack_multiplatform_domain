package dev.baseio.slackdomain.usecases.auth

import dev.baseio.slackdomain.datasources.remote.auth.SKAuthNetworkDataSource
import dev.baseio.slackdomain.model.users.DomainLayerUsers

class UseCaseCurrentUser(private val skAuthNetworkDataSource: SKAuthNetworkDataSource) {
  suspend operator fun invoke(): Result<DomainLayerUsers.SKUser> {
    return skAuthNetworkDataSource.getLoggedInUser()
  }
}
