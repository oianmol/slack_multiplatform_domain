package dev.baseio.slackdomain.model.users

import kotlinx.serialization.Serializable

interface DomainLayerUsers {
    @Serializable
    data class SKUser(
        val uuid: String = "",
        val workspaceId: String,
        val gender: String? = null,
        val name: String? = null,
        val location: String? = null,
        val email: String? = null,
        val username: String? = null,
        val userSince: Long? = null,
        val phone: String? = null,
        val avatarUrl: String? = null,
        val publicKey: SKUserPublicKey? = null
    )

    @Serializable
    data class SkAuthUser(
        val email: String,
        val password: String,
        val user: SKUser
    )

    @Serializable
    data class SKStatus(val information: String, val statusCode: String)

    @Serializable
    data class SKAuthResult(
        val token: String,
        val refreshToken: String,
        val status: SKStatus
    )

    @Serializable
    data class SKUserPublicKey(
        val algorithm: String,
        val keyBytes: ByteArray
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as SKUserPublicKey

            if (algorithm != other.algorithm) return false
            if (!keyBytes.contentEquals(other.keyBytes)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = algorithm.hashCode()
            result = 31 * result + keyBytes.contentHashCode()
            return result
        }
    }
}
