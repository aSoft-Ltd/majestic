package majestic.config

import kotlinx.serialization.Serializable

@Serializable
class ClientConfig(
    /**
     * The API endpoint for the application
     *
     * This is made nullable to allow web targets to be bundled without an API URL and instead rely on the browser's origin
     * Other targets (i.e. desktop, android, ios) must provide an API URL to connect to the server
     */
    val api: String? = null,
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