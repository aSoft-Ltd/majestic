package majestic.config

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import java.awt.Toolkit

private fun StartWindow.toDpSize(): DpSize {
    val screen = Toolkit.getDefaultToolkit().screenSize
    return DpSize(width.coerceIn(0, screen.width).dp, height.coerceIn(0, screen.height).dp)
}

private fun ClientConfig.toDpSize() = window?.toDpSize() ?: DpSize.Unspecified

private fun ClientConfig.toPlacement(): WindowPlacement {
    if (window == null) return WindowPlacement.Maximized
    val screen = Toolkit.getDefaultToolkit().screenSize
    return when {
        window.width >= screen.width -> WindowPlacement.Maximized
        window.height >= screen.height -> WindowPlacement.Maximized
        else -> WindowPlacement.Floating
    }
}

private fun ClientConfig.toPosition() = when (val w = window) {
    null -> WindowPosition(Alignment.Center)
    else -> run {
        val screen = Toolkit.getDefaultToolkit().screenSize
        val y = (screen.height - w.height) / 2
        val x = if (screen.width >= w.width) (screen.width - y - w.width) else 0
        WindowPosition.Absolute(x.dp, y.dp)
    }
}

@Composable
fun ClientConfig.toWindowState() = rememberWindowState(
    position = toPosition(),
    placement = toPlacement(),
    size = toDpSize()
)