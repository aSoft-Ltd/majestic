package majestic.config

import kotlinx.serialization.Serializable

@Serializable
class ClientConfig(
    /**
     * The API endpoint for the application
     */
    val api: String,
    /**
     * The start page for the application
     */
    val start: String? = null,
    /**
     * The directory to store files and configuration
     */
    val directory: String? = null,
    /**
     * The starting window configuration
     */
    val window: StartWindow? = null,

    /**
     * The database file name
     */
    val database: String = "database.db"
)