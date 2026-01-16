package majestic.users.profile.permissions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import majestic.users.tools.data.Permissions

internal enum class PermissionState {
    Main, Detailed
}

internal class PermissionScreen() {
    var view by mutableStateOf(PermissionState.Main)
    var activeObj by mutableStateOf<Permissions?>(null)
    fun main() {
        view = PermissionState.Main
    }

    fun detailed() {
        view = PermissionState.Detailed
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
