package majestic.users.profile.roles

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.icons.PlusIcon
import com.example.demo.icons.SchoolIcon
import composex.screen.orientation.ScreenOrientation

fun Modifier.bottomBorder(width: Float, color: Color) = this.drawBehind {
    drawLine(
        color = color, start = Offset(0f, size.height), end = Offset(size.width, size.height), strokeWidth = width
    )
}

@Composable
fun Campus(
    campusName: String,
    rolesCount: Int,
    onClick: () -> Unit,
    onClickPlus: () -> Unit,
    onClickMore: () -> Unit,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints {
        val isSm = maxWidth < Breakpoints.sm // use screen orientation, go into it to see how it works
        val horizontalPadding = if (isSm) 20.dp else 40.dp
        val verticalPadding = 15.dp

        var isHovered by remember { mutableStateOf(false) }

        val backgroundColor by animateColorAsState(
            targetValue = if (isHovered) Color.White.copy(alpha = 0.03f) else Color.Transparent,
            animationSpec = tween(durationMillis = 300, easing = EaseOut)
        )

        val items = buildList {
            if (isSm) {
                add(
                    MenuItem("Add Role") { onClickPlus() },
                )
            }
            add(MenuItem("View Role") { })
            add(MenuItem("Edit Role") { })
            add(MenuItem("Delete Role", color = Color(0xFFF87171)) { })
        }

        Row(
            modifier = modifier.fillMaxWidth().bottomBorder(0.2f, Color.White) // check youtube tutorial on how to use majestic colors
                .bottomBorder(0.2f, Color.White.copy(alpha = 0.2f)).background(
                    backgroundColor
                ).clickable { onClick() }.pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            when (event.type) {
                                PointerEventType.Enter -> isHovered = true
                                PointerEventType.Exit -> isHovered = false
                            }
                        }
                    }
                }.padding(horizontal = horizontalPadding, vertical = verticalPadding),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(44.dp).clip(RoundedCornerShape(6.dp)).background(Color(0xFF1D2430))
                        .background(Color(0xFF000000).copy(alpha = 0.10f)).border(
                            1.dp, Color(0xFF1D2430), RoundedCornerShape(6.dp)
                        ), contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = SchoolIcon, contentDescription = "School Icon", tint = Color(0xFF4DB6AC))
                }

                Column {
                    Text(
                        text = campusName,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 25.2.sp,
                    )
                    Text(
                        text = "$rolesCount ${if (rolesCount > 1) "Roles" else "Role"}",
                        color = Color.White.copy(alpha = 0.30f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight(450),
                        lineHeight = 19.6.sp,
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                if (!isSm) IconButton(
                    icon = IconSource.Vector(PlusIcon), contentDescription = "Plus Icon", onClick = onClickPlus
                )

                CustomMoreMenu(
                    items = items, modifier = Modifier.wrapContentSize(Alignment.TopEnd)
                )
            }
        }
    }
}