package dev.baseio.slackdomain.usecases.channels


import dev.baseio.slackdomain.datasources.IDataDecryptor
import dev.baseio.slackdomain.model.message.DomainLayerMessages
import dev.baseio.slackdomain.datasources.local.channels.SKLocalDataSourceChannelLastMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UseCaseFetchChannelsWithLastMessage(
  private val SKLocalDataSourceChannelLastMessage: SKLocalDataSourceChannelLastMessage,
  private val iDataDecryptor: IDataDecryptor
) {

  operator fun invoke(workspaceId: String): Flow<List<DomainLayerMessages.SKLastMessage>> {
    return SKLocalDataSourceChannelLastMessage.fetchChannelsWithLastMessage(workspaceId = workspaceId).map {
      it.map { skLastMessage ->
        var messageFinal = skLastMessage.message
        runCatching {
          messageFinal =
            messageFinal.copy(decodedMessage = iDataDecryptor.decrypt(messageFinal.message).decodeToString())
        }.exceptionOrNull()
          ?.let {
            kotlin.runCatching {
              messageFinal =
                messageFinal.copy(decodedMessage = iDataDecryptor.decrypt(messageFinal.localMessage).decodeToString())
            }
          }
        skLastMessage.copy(message = messageFinal)
      }
    }
  }

}