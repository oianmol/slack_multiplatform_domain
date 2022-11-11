package dev.baseio.slackdomain.model.channel

import dev.baseio.slackdomain.model.users.DomainLayerUsers
import kotlinx.datetime.Clock


interface DomainLayerChannels {

  sealed class SKChannel(
    var workspaceId: String,
    var channelId: String,
    var pictureUrl: String? = null,
    var channelName: String? = null,
    val publicKey: DomainLayerUsers.SKUserPublicKey
  ) {
    data class SkDMChannel(
      var uuid: String,
      val workId: String,
      var senderId: String,
      var receiverId: String,
      val createdDate: Long = Clock.System.now().toEpochMilliseconds(),
      val modifiedDate: Long = Clock.System.now().toEpochMilliseconds(),
      val deleted: Boolean,
      val userPublicKey: DomainLayerUsers.SKUserPublicKey
    ) : SKChannel(workId, uuid, publicKey = userPublicKey)

    data class SkGroupChannel(
      var uuid: String,
      val workId: String,
      var name: String,
      val createdDate: Long = Clock.System.now().toEpochMilliseconds(),
      val modifiedDate: Long = Clock.System.now().toEpochMilliseconds(),
      var avatarUrl: String?,
      val deleted: Boolean,
      val userPublicKey: DomainLayerUsers.SKUserPublicKey
    ) : SKChannel(workId, uuid, channelName = name, pictureUrl = avatarUrl, publicKey = userPublicKey)
  }

  data class SkChannelMember(
    val uuid: String,
    val workspaceId: String,
    val channelId: String,
    val memberId: String
  )
}
