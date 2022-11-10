package dev.baseio.slackdomain.datasources

interface PublicKeyRetriever {
    suspend fun retrieve(sender: String, channelId: String, workspaceId: String): ByteArray
}