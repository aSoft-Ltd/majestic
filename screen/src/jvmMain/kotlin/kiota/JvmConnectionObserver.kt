package kiota

import cinematic.mutableLiveOf
import java.net.NetworkInterface
import java.util.Locale


class JvmConnectionObserver(
    val verifier: ConnectionVerifier
) : ConnectionObserver {

    override val status by lazy { mutableLiveOf<Connection>(Disconnected) }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    fun interfaces() {
        val networkInterfaces = NetworkInterface.getNetworkInterfaces()
        while (networkInterfaces.hasMoreElements()) {
            val ni = networkInterfaces.nextElement()

            // Exclude loopback and virtual interfaces

            if (ni.isLoopback || ni.isVirtual || !ni.isUp) {
                continue
            }

            val name = ni.displayName.lowercase(Locale.ENGLISH)


            // Check for common indicators of Wi-Fi or Ethernet
            if (name.contains("wireless") || name.contains("wifi")) {
                println("Connected via Wi-Fi: $name")
            } else if (name.contains("ethernet") || name.contains("eth")) {
                println("Connected via Ethernet: $name")
            } else {
                println("Other active interface: $name")
            }
        }
    }
}