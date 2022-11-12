package dev.baseio.slackdomain.datasources

import dev.baseio.slackdomain.model.users.DomainLayerUsers

interface SKPublicKeyRetriever {
    fun get(uuid: String): DomainLayerUsers.SKUserPublicKey
}