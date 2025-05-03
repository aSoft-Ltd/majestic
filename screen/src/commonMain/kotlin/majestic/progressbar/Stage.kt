package majestic.progressbar

import androidx.compose.runtime.Composable

class Stage(val content: @Composable (Status) -> Unit) {
    enum class Status {
        Complete,
        Current,
        Pending
    }
}
