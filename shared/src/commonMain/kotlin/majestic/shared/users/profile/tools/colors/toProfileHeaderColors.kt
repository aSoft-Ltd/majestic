package majestic.shared.users.profile.tools.colors

import androidx.compose.ui.graphics.compositeOver
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Dark
import majestic.Light
import majestic.ThemeColor
import majestic.shared.users.profile.tools.types.FlowItemColors
import majestic.shared.users.profile.tools.types.HeadColors

fun ThemeColor.toProfileHeaderColors(orientation: ScreenOrientation) =
    HeadColors(
        content = if (this is Dark) surface.contra.color else surface.actual.color,
        background = surface.contra.color,
        flow = FlowItemColors(
            icon = surface.contra.color.copy(.3f)
                .compositeOver(dominant.actual.color.copy(.3f)),
            title = surface.contra.color,
            summary = surface.contra.color.copy(.5f)
        ),
        title = when (orientation) {
            is Portrait -> when (this) {
                is Dark -> surface.contra.color
                is Light -> surface.actual.color
            }

            is Landscape -> surface.contra.color
        }
    )