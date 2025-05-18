package majestic.config

import kotlinx.serialization.Serializable

@Serializable
class ClientConfig(
    val api: String,
    val start: String? = null,
    val window: StartWindow? = null
)