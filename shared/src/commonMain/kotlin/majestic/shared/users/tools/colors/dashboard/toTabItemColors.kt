package majestic.shared.users.tools.colors.dashboard

import majestic.ThemeColor
import majestic.shared.users.dashboard.TabItemColors


internal fun ThemeColor.toTabItemColors() = TabItemColors(
    surfaceContra = surface.contra.color,
    dominantActual = dominant.actual.color
)