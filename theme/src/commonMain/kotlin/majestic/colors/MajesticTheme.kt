package majestic.colors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import majestic.colors.options.ACADEMIA_LIGHT

val MajesticColors = compositionLocalOf { ACADEMIA_LIGHT }

@Composable
fun MajesticColorProvider(
    colors: ThemeColors = ACADEMIA_LIGHT,
    content: @Composable () -> Unit
) = CompositionLocalProvider(MajesticColors.provides(colors), content)