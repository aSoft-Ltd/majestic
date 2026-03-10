package majestic.shared.tools.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.separator

fun Modifier.modalHeaderStyle(colors: ModalColors): Modifier = this
    .clipToBounds()
    .fillMaxWidth()
    .background(colors.header)
    .padding(vertical = 16.dp, horizontal = 16.dp)

fun Modifier.modalSubheaderStyle(colors: ModalColors) = this
    .fillMaxWidth()
    .background(colors.subheader)
    .separator(colors.subheaderBorder, 0.5.dp)
    .padding(horizontal = 16.dp, vertical = 16.dp)

fun Modifier.modalFooterStyle(
    scope: ColumnScope,
): Modifier = with(scope) {
    this@modalFooterStyle
        .align(Alignment.End)
        .clipToBounds()
        .fillMaxWidth()
        .padding(vertical = 16.dp, horizontal = 16.dp)
}

fun Modifier.modalPopupStyle(
    orientation: ScreenOrientation,
    colors: ModalColors,
    width: Dp = 500.dp,
    heightIn: Pair<Dp, Dp> = Pair(300.dp, 600.dp)
) = when (orientation) {
    Landscape -> this
        .width(width)
        .heightIn(heightIn.first, heightIn.second)
        .clip(RoundedCornerShape(20.dp))
        .background(colors.body)

    Portrait -> this
        .fillMaxSize()
        .clipToBounds()
        .background(colors.body)
}