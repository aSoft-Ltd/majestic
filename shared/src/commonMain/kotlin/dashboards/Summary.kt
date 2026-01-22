package dashboards

import org.jetbrains.compose.resources.DrawableResource

data class Summary<T>(
    val label: String,
    val value: Int,
    val percentage: Float,
    val dataStatus: T? = null,
    val summaryStatus: SummaryStatus? = null,
    val icon: DrawableResource,
)
