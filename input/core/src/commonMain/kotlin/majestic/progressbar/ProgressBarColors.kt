package majestic.progressbar

import androidx.compose.ui.graphics.Color

data class ProgressBarColors(
    val current: Color,
    val complete: Color
) {
    companion object {
        val Default = ProgressBarColors(
            current = Color(0xFF0061FF),
            complete = Color(0xFF7AC55C)
        )
    }
}
