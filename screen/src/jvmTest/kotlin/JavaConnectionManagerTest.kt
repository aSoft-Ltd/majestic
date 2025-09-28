import io.ktor.client.HttpClient
import kiota.HttpConnectionVerifier
import kiota.JvmConnectionObserver
import kotlin.test.Test
import kotlin.test.fail

class JavaConnectionManagerTest {
    val verifier = HttpConnectionVerifier(
        hosts = listOf("https://google.com", "https://asoft.co.tz"),
        http = HttpClient { }
    )

    @Test
    fun should_detect_the_availability_of_a_connection() {
        val connection = JvmConnectionObserver(verifier)

        connection.interfaces()
        fail("Finish writing this test")
    }
}