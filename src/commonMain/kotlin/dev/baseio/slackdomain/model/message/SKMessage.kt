package dev.baseio.slackdomain.model.message

import dev.baseio.slackdomain.model.channel.DomainLayerChannels
import dev.baseio.slackdomain.model.users.DomainLayerUsers


interface DomainLayerMessages {
    data class SKMessage(
        val uuid: String,
        val workspaceId: String,
        val channelId: String,
        val message: ByteArray,
        val sender: String,
        val createdDate: Long,
        val modifiedDate: Long,
        var isDeleted: Boolean = false,
        var isSynced: Boolean = false,
        var localMessage: ByteArray,
        var decodedMessage: String = ""
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as SKMessage

            if (uuid != other.uuid) return false
            if (workspaceId != other.workspaceId) return false
            if (channelId != other.channelId) return false
            if (!message.contentEquals(other.message)) return false
            if (sender != other.sender) return false
            if (createdDate != other.createdDate) return false
            if (modifiedDate != other.modifiedDate) return false
            if (isDeleted != other.isDeleted) return false
            if (isSynced != other.isSynced) return false
            if (!localMessage.contentEquals(other.localMessage)) return false
            if (decodedMessage != other.decodedMessage) return false

            return true
        }

        override fun hashCode(): Int {
            var result = uuid.hashCode()
            result = 31 * result + workspaceId.hashCode()
            result = 31 * result + channelId.hashCode()
            result = 31 * result + message.contentHashCode()
            result = 31 * result + sender.hashCode()
            result = 31 * result + createdDate.hashCode()
            result = 31 * result + modifiedDate.hashCode()
            result = 31 * result + isDeleted.hashCode()
            result = 31 * result + isSynced.hashCode()
            result = 31 * result + localMessage.contentHashCode()
            result = 31 * result + decodedMessage.hashCode()
            return result
        }

    }

    data class SKLastMessage(
        val channel: DomainLayerChannels.SKChannel,
        val message: SKMessage
    )
}