package majestic.progressbar

import androidx.compose.runtime.Composable

class ProgressBarIcon(
    val default: @Composable () -> Unit,
    val active: @Composable () -> Unit,
    val complete: @Composable () -> Unit
)
