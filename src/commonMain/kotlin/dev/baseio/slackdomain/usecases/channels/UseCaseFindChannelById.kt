package dev.baseio.slackdomain.usecases.channels

import dev.baseio.slackdomain.datasources.local.channels.SKLocalDataSourceReadChannels
import dev.baseio.slackdomain.CoroutineDispatcherProvider
import dev.baseio.slackdomain.LOGGED_IN_USER
import dev.baseio.slackdomain.datasources.local.SKLocalKeyValueSource
import dev.baseio.slackdomain.model.channel.DomainLayerChannels
import dev.baseio.slackdomain.model.users.DomainLayerUsers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class UseCaseFindChannelById(
    private val sdkDataSource: SKLocalDataSourceReadChannels,
    private val skKeyValueData: SKLocalKeyValueSource,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) {
  suspend operator fun invoke(workspaceId: String, uuid: String): DomainLayerChannels.SKChannel? {
    return withContext(coroutineDispatcherProvider.io) {
      //TODO check network source for channel by id also!
      val firstUserChannelById = sdkDataSource.getChannelById(workspaceId, uuid)
      val user = Json.decodeFromString<DomainLayerUsers.SKUser>(skKeyValueData.get(LOGGED_IN_USER)!!)
      val secondUserChannelById = sdkDataSource.getChannelById(workspaceId, user.uuid)
      firstUserChannelById ?: secondUserChannelById
    }
  }
}