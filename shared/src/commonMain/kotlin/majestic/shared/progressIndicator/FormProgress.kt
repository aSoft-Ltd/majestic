package majestic.shared.progressIndicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.IconCheckCircle
import majestic.IconCheckColors
import majestic.ThemeColor
import majestic.loaders.AnimatedDots
import majestic.loaders.animateDots
import majestic.progressbar.LineProgressBar
import majestic.progressbar.ProgressBarColors
import majestic.progressbar.ProgressBarIcon
import majestic.progressbar.Stage

data class FormProgressColors(
    val bar: ProgressBarColors,
    val check: IconCheckColors,
    val pending: Color,
    val dots: Color,
    val animateDots: Color
) {
    companion object {
        fun build(theme: ThemeColor) = FormProgressColors(
            bar = ProgressBarColors(
                current = theme.surface.contra.color.copy(0.50f),
                complete = theme.surface.contra.color.copy(0.50f),
            ),
            check = IconCheckColors.Default.copy(
                icon = ColorPair(
                    background = Color(0xFF46C362),
                    foreground = theme.surface.actual.color
                )
            ),
            pending = theme.surface.contra.color.copy(0.20f),
            dots = theme.surface.contra.color.copy(0.70f),
            animateDots = theme.surface.contra.color.copy(0.10f)
        )
    }
}

@Composable
fun FormProgress(
    stages: List<Stage>,
    current: Int,
    colors: FormProgressColors,
    showLabel: Boolean = true,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    LineProgressBar(
        modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
        steps = stages.size,
        current = current,
        colors = colors.bar,
        icon = ProgressBarIcon(
            active = {
                AnimatedDots(
                    modifier = Modifier.animateDots(colors.animateDots),
                    color = colors.dots,
                    duration = 200,
                    delay = 200,
                    size = 3.dp
                )
            },
            complete = {
                IconCheckCircle(
                    selected = true,
                    colors = colors.check
                )
            },
            pending = {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(colors.pending)
                )
            }
        )
    )
    if (showLabel) LabelsOnTrack(
        stages = stages,
        modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp, top = 12.dp)
    )
}

@Composable
private fun LabelsOnTrack(
    stages: List<Stage>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        stages.forEachIndexed { index, st ->
            Box(
                modifier = Modifier.width(0.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.wrapContentWidth(unbounded = true),
                    contentAlignment = Alignment.Center
                ) {
                    st.content(Stage.Status.Pending)
                }
            }
            if (index < stages.size - 1) {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
