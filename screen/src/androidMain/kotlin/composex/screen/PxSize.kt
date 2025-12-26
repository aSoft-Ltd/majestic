package composex.screen

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.window.layout.WindowMetricsCalculator


@Composable
actual fun rememberScreenSizeInPx(): IntSize {
    val context = LocalContext.current

    val activity = context.findActivity()

    return if (activity != null) {
        val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)
        val bounds = metrics.bounds
        IntSize(bounds.width(), bounds.height())
    } else {
        val configuration = LocalConfiguration.current
        val dpi = configuration.densityDpi
        IntSize(
            width = configuration.screenWidthDp * dpi,
            height = configuration.screenHeightDp * dpi
        )
    }
}

// @Composable
// actual fun rememberScreenSizeInPx(): IntSize {
//     val configuration = LocalConfiguration.current
//     val dpi = configuration.densityDpi
//     val width = configuration.screenWidthDp * dpi
//     val height = configuration.screenHeightDp * dpi
//     return IntSize(width, height)
// }


private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}