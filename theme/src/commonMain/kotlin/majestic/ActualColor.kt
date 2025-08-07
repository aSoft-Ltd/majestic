package majestic

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import kotlin.jvm.JvmInline

@JvmInline
value class ActualColor(val color: Color) {
    companion object {
        fun of(@ColorInt color: Long) = ActualColor(Color(color))
    }
}