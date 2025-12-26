package composex.screen

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.window.layout.WindowMetricsCalculator


@Composable
actual fun rememberScreenSizeInPx(): IntSize {
    val context = LocalContext.current
    val density = LocalDensity.current

    val activity = context.findActivity()

    return if (activity != null) {
        val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)
        val bounds = metrics.bounds
        IntSize(bounds.width(), bounds.height())
    } else {
        val size = rememberScreenSizeInDp()
        with(density) {
            IntSize(
                width = size.width.toPx().toInt(),
                height = size.height.toPx().toInt()
            )
        }
    }
}


private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}