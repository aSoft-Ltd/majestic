package majestic.users.dashboard.tools


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

internal sealed interface View
internal object Users : View
internal object Roles : View

internal class ActiveView {
    var view by mutableStateOf<View>(Users)
}

@Composable
internal fun rememberActiveView() = remember { ActiveView() }