package com.example.demo.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.demo.icons.MoreIcon

data class MenuItem(
    val label: String,
    val color: Color = Color.White,
    val onClick: () -> Unit
)

@Composable
fun CustomMoreMenu(items: List<MenuItem>, menuWidth: Dp = 180.dp, modifier: Modifier = Modifier) {
    var showPopup by remember { mutableStateOf(false) }

    val base = Color(0xFF191F29)
    val overlay = Color.White.copy(alpha = 0.01f)

    val mixedBackground = overlay.compositeOver(base)

//    I am using box to anchor the popup to this location
    Box(modifier = modifier) {
        IconButton(icon = IconSource.Vector(MoreIcon), contentDescription = "More Icon", onClick = { showPopup = true })

        if (showPopup) {
            val yOffset = with(LocalDensity.current) { 48.dp.roundToPx() }

            Popup(
                alignment = Alignment.TopEnd,
                offset = IntOffset(x = 0, y = yOffset),
                onDismissRequest = { showPopup = false },
                properties = PopupProperties(focusable = true),
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = mixedBackground,
                    shadowElevation = 8.dp,
                    modifier = Modifier.width(menuWidth)
                ) {
                    Column {
                        items.forEach { item ->
                            CustomMenuItem(
                                text = item.label,
                                color = item.color
                            ) {
                                showPopup = false
                                item.onClick()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomMenuItem(text: String, color: Color, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Text(
        text = text,
        color = color,
        modifier = Modifier.fillMaxWidth().hoverable(interactionSource)
            .clickable(interactionSource = interactionSource, indication = null, onClick = onClick).background(
                if (isHovered) Color.White.copy(alpha = 0.03f) else Color.Transparent
            ).padding(horizontal = 14.dp, vertical = 14.dp)
    )
}