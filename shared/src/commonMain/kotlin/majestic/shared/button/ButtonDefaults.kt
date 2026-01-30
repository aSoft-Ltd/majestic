package majestic.shared.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import majestic.Dark
import majestic.Light
import majestic.ThemeColor

private val RedDestructive = Color(0xFFEF5350)
private val GreenSuccess = Color(0xFF00C853)

val ThemeColor.destructive: Color
// TODO: Consult with designer to see if these could be different for light and dark modes

    get() = when (this) {
        is Light -> RedDestructive
        is Dark -> RedDestructive
    }

val ThemeColor.success: Color
    get() = when (this) {
        is Light -> GreenSuccess
        is Dark -> GreenSuccess
    }

internal fun ThemeColor.toButtonColors(
    variant: ButtonVariant,
    enabled: Boolean,
): ButtonColors {
    val alpha = if (enabled) 1f else 0.4f
    val surfaceActual = surface.actual.color
    val surfaceContra = surface.contra.color
    val dominantActual = dominant.actual.color

    return when (variant) {
        is ButtonVariant.Primary.Default -> {
            val bgColor = when (this) {
                is Light -> dominantActual
                is Dark -> surfaceContra
            }

            ButtonColors(
                background = bgColor.copy(alpha = alpha),
                foreground = surfaceActual
            )
        }

        is ButtonVariant.Primary.Destructive -> ButtonColors(
            background = Color(this.destructive.value).copy(alpha = alpha), // can these colors be constants somewhere?
            foreground = Color.White
        )

        is ButtonVariant.Primary.Success -> ButtonColors(
            background = Color(this.success.value).copy(alpha = alpha),
            foreground = Color.White
        )
        // opaque
        is ButtonVariant.Secondary.Opaque.Default -> {
            ButtonColors(
                background = Color.Transparent,
                foreground = surfaceContra.copy(alpha = alpha)
            )
        }

        is ButtonVariant.Secondary.Opaque.Destructive -> ButtonColors(
            background = Color.Transparent,
            foreground = Color(this.destructive.value).copy(alpha = alpha)
        )

        is ButtonVariant.Secondary.Opaque.Success -> ButtonColors(
            background = Color.Transparent,
            foreground = Color(this.success.value).copy(alpha = alpha)
        )
        // fully transparent
        is ButtonVariant.Secondary.Transparent.Default -> ButtonColors(
            background = Color.Transparent,
            foreground = surfaceContra.copy(alpha = alpha)
        )

        is ButtonVariant.Secondary.Transparent.Destructive -> ButtonColors(
            background = Color.Transparent,
            foreground = Color(this.destructive.value).copy(alpha = alpha)
        )

        is ButtonVariant.Secondary.Transparent.Success -> ButtonColors(
            background = Color.Transparent,
            foreground = Color(this.success.value).copy(alpha = alpha)
        )
        // outline
        is ButtonVariant.Secondary.Outlined.Default -> ButtonColors(
            background = Color.Transparent,
            foreground = surfaceContra.copy(alpha = alpha),
            border = surfaceContra.copy(alpha = 0.1f * alpha)
        )

        is ButtonVariant.Secondary.Outlined.Destructive -> ButtonColors(
            background = Color.Transparent,
            foreground = Color(this.destructive.value).copy(alpha = alpha),
            border = Color(this.destructive.value).copy(alpha = 0.1f * alpha)
        )

        is ButtonVariant.Secondary.Outlined.Success -> ButtonColors(
            background = Color.Transparent,
            foreground = Color(this.success.value).copy(alpha = alpha),
            border = Color(this.success.value).copy(alpha = 0.1f * alpha)
        )
    }
}

internal fun ButtonSize.toDp() = when (this) {
    ButtonSize.Small -> 16
    ButtonSize.Medium -> 18
    ButtonSize.Large -> 20
}.dp

internal fun ButtonSize.toPadding() = when (this) {
    ButtonSize.Small -> PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ButtonSize.Medium -> PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ButtonSize.Large -> PaddingValues(horizontal = 16.dp, vertical = 12.dp)
}