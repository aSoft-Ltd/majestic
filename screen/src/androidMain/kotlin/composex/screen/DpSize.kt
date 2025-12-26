package composex.screen

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator


@Composable
actual fun rememberScreenSizeInDp(): DpSize {
    val context = LocalContext.current
    val density = LocalDensity.current

    val activity = context.findActivity()

    return if (activity != null) {
        val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)
        val bounds = metrics.bounds
        with(density) {
            DpSize(
                width = bounds.width().toDp(),
                height = bounds.height().toDp()
            )
        }
    } else {
        val configuration = LocalConfiguration.current
        DpSize(
            width = configuration.screenWidthDp.dp,
            height = configuration.screenHeightDp.dp
        )
    }
}


private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}