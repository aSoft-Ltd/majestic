package majestic.progressbar

import androidx.compose.runtime.Composable

class ProgressBarIcon(
    val pending: @Composable () -> Unit,
    val active: @Composable () -> Unit,
    val complete: @Composable () -> Unit
)
