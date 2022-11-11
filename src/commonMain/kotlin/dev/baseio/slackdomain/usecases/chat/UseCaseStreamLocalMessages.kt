package dev.baseio.slackdomain.usecases.chat

import dev.baseio.slackdomain.datasources.IDataDecryptor
import dev.baseio.slackdomain.datasources.local.messages.SKLocalDataSourceMessages
import dev.baseio.slackdomain.model.message.DomainLayerMessages
import dev.baseio.slackdomain.usecases.channels.UseCaseWorkspaceChannelRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UseCaseStreamLocalMessages(
  private val skLocalDataSourceMessages: SKLocalDataSourceMessages,
  private val iDataDecryptor: IDataDecryptor
) {
  operator fun invoke(useCaseWorkspaceChannelRequest: UseCaseWorkspaceChannelRequest): Flow<List<DomainLayerMessages.SKMessage>> {
    return skLocalDataSourceMessages.streamLocalMessages(
      workspaceId = useCaseWorkspaceChannelRequest.workspaceId,
      useCaseWorkspaceChannelRequest.channelId!!,
    ).map { skMessageList ->
      skMessageList.map { skMessage ->
        kotlin.runCatching {
          skMessage.decodedMessage =
            iDataDecryptor.decrypt(skMessage.message, chainId = skMessage.channelId).decodeToString()
        }.exceptionOrNull()?.let { throwable ->
          kotlin.runCatching {
            skMessage.decodedMessage =
              iDataDecryptor.decrypt(skMessage.message, chainId = skMessage.channelId).decodeToString()
          }
        }
        skMessage
      }
    }
  }
}