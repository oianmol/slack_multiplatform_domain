package dev.baseio.slackdomain.usecases.chat

import dev.baseio.slackdomain.datasources.IDataEncrypter
import dev.baseio.slackdomain.datasources.PublicKeyRetriever
import dev.baseio.slackdomain.model.message.DomainLayerMessages
import dev.baseio.slackdomain.datasources.local.messages.SKLocalDataSourceMessages
import dev.baseio.slackdomain.datasources.remote.messages.SKNetworkDataSourceMessages

class UseCaseSendMessage(
  private val SKLocalDataSourceMessages: SKLocalDataSourceMessages,
  private val skNetworkDataSourceMessages: SKNetworkDataSourceMessages,
  private val iDataEncrypter: IDataEncrypter,
  private val publicKeyRetriever: PublicKeyRetriever
) {
  suspend operator fun invoke(params: DomainLayerMessages.SKMessage): DomainLayerMessages.SKMessage {
    val message =
      skNetworkDataSourceMessages.sendMessage(
        params.copy(
          message = iDataEncrypter.encrypt(
            params.message,
            publicKeyRetriever.retrieve(params.sender, params.channelId, params.workspaceId), chainId = params.channelId
          ),
        )
      )
    return SKLocalDataSourceMessages.saveMessage(
      message.copy(
        localMessage = iDataEncrypter.encrypt(
          params.message,
          publicKeyRetriever.getMyPublicKey(params.workspaceId, params.sender), chainId = params.channelId
        )
      )
    )
  }
}
