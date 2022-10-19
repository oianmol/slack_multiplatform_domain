package dev.baseio.slackdomain.datasources.remote.channels

import dev.baseio.slackdomain.model.channel.DomainLayerChannels

interface SKNetworkSourceChannel {
  suspend fun inviteUserToChannel(userId: String, channelId: String): List<DomainLayerChannels.SkChannelMember>
}