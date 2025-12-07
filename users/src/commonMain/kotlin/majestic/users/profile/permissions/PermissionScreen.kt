package majestic.users.profile.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import majestic.users.tools.data.Permissions

internal sealed interface PermissionScreenState
internal object Main : PermissionScreenState
internal object Detailed : PermissionScreenState

internal class PermissionScreen() {
    var view by mutableStateOf<PermissionScreenState?>(Main)
    var activeObj by mutableStateOf<Permissions?>(null)
    fun main() {
        view = Main
    }

    fun detailed() {
        view = Detailed
    }

    fun reset() {
        activeObj = null
    }

    fun set(obj: Permissions) {
        activeObj = obj
    }
}

@Composable
internal fun rememberPermissionScreenState() = remember { PermissionScreen() }