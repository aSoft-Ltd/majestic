package majestic

import androidx.compose.runtime.Composable

class MenuGraphic(
    val leading: (@Composable () -> Unit)? = null,
    val trailing: (@Composable () -> Unit)? = null
)