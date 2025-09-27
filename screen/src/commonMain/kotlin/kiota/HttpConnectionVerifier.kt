package kiota

import io.ktor.client.HttpClient
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import kotlinx.coroutines.CancellationException
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class HttpConnectionVerifier(
    private val hosts: List<String>,
    private val timeout: Duration = 30.seconds,
    private val http: HttpClient
) : ConnectionVerifier {
    override suspend fun verify(): Connection {
        if (hosts.isEmpty()) return Disconnected
        val candidates = hosts.shuffled().toMutableSet()
        do {
            val host = candidates.random()
            candidates.remove(host)
            try {
                val response = http.get(host) {
                    timeout { requestTimeoutMillis = timeout.inWholeMilliseconds }
                }
                if (response.status.value in 200..299) return Connected(verified = host)
            } catch (ce: CancellationException) {
                throw ce
            } catch (_: Exception) {
            }
        } while (candidates.isNotEmpty())
        return Disconnected
    }
}