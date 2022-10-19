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
    val avatarUrl: String? = null
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
}
