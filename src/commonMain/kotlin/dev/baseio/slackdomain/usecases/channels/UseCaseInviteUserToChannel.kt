package dev.baseio.slackdomain.usecases.channels

import dev.baseio.slackdomain.datasources.remote.channels.SKNetworkSourceChannel
import dev.baseio.slackdomain.model.channel.DomainLayerChannels

class UseCaseInviteUserToChannel(private val networkSourceChannels: SKNetworkSourceChannel) {
  suspend operator fun invoke(userId: String, channelId: String): Result<List<DomainLayerChannels.SkChannelMember>> {
    return kotlin.runCatching {
      networkSourceChannels.inviteUserToChannel(userId, channelId)
    }
  }
}