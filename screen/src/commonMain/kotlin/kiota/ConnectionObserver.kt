package kiota

import androidx.compose.runtime.State
import kiota.connectivity.Connection

interface ConnectionObserver {
    val status: State<Connection>
}