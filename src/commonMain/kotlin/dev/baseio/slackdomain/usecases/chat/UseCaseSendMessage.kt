package dev.baseio.slackdomain.usecases.chat

import dev.baseio.slackdomain.model.message.DomainLayerMessages
import dev.baseio.slackdomain.datasources.local.messages.SKLocalDataSourceMessages
import dev.baseio.slackdomain.datasources.remote.messages.SKNetworkDataSourceMessages

class UseCaseSendMessage(
  private val SKLocalDataSourceMessages: SKLocalDataSourceMessages,
  private val skNetworkDataSourceMessages: SKNetworkDataSourceMessages,

  ) {
  suspend operator fun invoke(params: DomainLayerMessages.SKMessage): DomainLayerMessages.SKMessage {
    val message =
      skNetworkDataSourceMessages.sendMessage(
        params
      )
    return SKLocalDataSourceMessages.saveMessage(
      message, params.message
    )
  }
}
