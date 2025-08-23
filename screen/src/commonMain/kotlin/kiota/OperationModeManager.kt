package kiota

import androidx.compose.runtime.State
import kiota.connectivity.Mode

interface OperationModeManager {
    val status: State<Mode>
    fun online()
    fun offline()
}