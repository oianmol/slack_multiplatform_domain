package dev.baseio.slackdomain.datasources.local.messages

import dev.baseio.slackdomain.model.message.DomainLayerMessages

interface IMessageDecrypter {
    fun decrypted(message: DomainLayerMessages.SKMessage): DomainLayerMessages.SKMessage?
}