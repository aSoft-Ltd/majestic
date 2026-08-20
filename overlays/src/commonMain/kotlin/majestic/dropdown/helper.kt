package majestic.dropdown

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.popupMaxHeight(maxHeight: Dp): Modifier = then(HeightElement(maxHeight))

internal fun Modifier.popupMaxHeight(): Dp = foldIn(300.dp) { maxHeight, element ->
    when (element) {
        is HeightElement -> element.maxHeight
        else -> maxHeight
    }
}

internal fun Modifier.overlayWidth(
    popupWidth: Dp?,
    intrinsicWidth: Boolean = true
): Modifier {
    return then(
        when {
            popupWidth != null -> Modifier.width(popupWidth)
            intrinsicWidth -> Modifier.width(IntrinsicSize.Max)
            else -> Modifier
        }
    )
}

fun Modifier.overlayModifier(
    popupWidth: Dp?,
    intrinsicWidth: Boolean = true,
    popupMaxHeight: Dp = 300.dp
): Modifier {
    return overlayWidth(
        popupWidth = popupWidth,
        intrinsicWidth = intrinsicWidth
    ).heightIn(max = popupMaxHeight)
}