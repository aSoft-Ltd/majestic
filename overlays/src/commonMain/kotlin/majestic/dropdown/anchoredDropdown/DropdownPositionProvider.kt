package majestic.dropdown.anchoredDropdown

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider

internal class DropdownPositionProvider(
    private val alignment: DropdownAlignment,
    private val gap: Int,
    private val shadowPadding: Int
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        val width = (popupContentSize.width - shadowPadding * 2).coerceAtLeast(0)
        val height = (popupContentSize.height - shadowPadding * 2).coerceAtLeast(0)
        val minX = shadowPadding
        val maxX = (windowSize.width - width - shadowPadding).coerceAtLeast(minX)
        val start = anchorBounds.left + Alignment.Start.align(width, anchorBounds.width, layoutDirection)
        val end = anchorBounds.left + Alignment.End.align(width, anchorBounds.width, layoutDirection)
        val left = when (alignment) {
            DropdownAlignment.Auto -> if (start in minX..maxX) start else end
            DropdownAlignment.Start -> start
            DropdownAlignment.Center -> anchorBounds.left + Alignment.CenterHorizontally.align(
                width,
                anchorBounds.width,
                layoutDirection
            )
            DropdownAlignment.End -> end
        }.coerceIn(minX, maxX)
        val minY = shadowPadding
        val maxY = (windowSize.height - height - shadowPadding).coerceAtLeast(minY)
        val below = anchorBounds.bottom + gap
        val above = anchorBounds.top - gap - height
        val top = when {
            below <= maxY -> below
            above >= minY -> above
            windowSize.height - anchorBounds.bottom >= anchorBounds.top -> below
            else -> above
        }.coerceIn(minY, maxY)

        return IntOffset(left - shadowPadding, top - shadowPadding)
    }
}