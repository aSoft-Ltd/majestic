package com.example.demo.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.IconButton
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_access
import tz.co.asoft.majestic_users.generated.resources.ic_square_lock

data class ModalAction(
    val name: String, val onClick: () -> Unit, val enabled: Boolean = true
)

@Composable
fun Modal(
    title: String,
    primaryAction: ModalAction,
    secondaryAction: ModalAction,
    onDismiss: () -> Unit,
    content: @Composable (() -> Unit)
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize().background(Color(0xCC15181D)).clickable(
            onClick = onDismiss, indication = null, interactionSource = remember { MutableInteractionSource() }),
        contentAlignment = Alignment.Center
    ) {
        val isMobile = maxWidth < Breakpoints.md
        val modalShape = RoundedCornerShape(18.dp)

        Column(
            modifier = Modifier.fillMaxWidth(if (isMobile) 0.9f else 0.4f).fillMaxHeight(if (isMobile) 0.9f else 0.9f)
                .clip(modalShape).background(Color(0xFF1A1F2E)).clickable(enabled = false) {}) {
//            Modal Header
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().background(Color(0xFF202733)).padding(20.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Icon(
                        painter = painterResource(Res.drawable.ic_access),
                        contentDescription = "Account Settings Filled",
                        tint = Color(0xFFD18C27),
                        modifier = Modifier.clip(RoundedCornerShape(6.dp))
//                    compositeOver for mixing colors
                            .background(Color(0xFF000000).copy(alpha = 0.10f)).background(Color(0xFF1D2430))
                            .padding(12.dp).size(24.dp)
                    )



                    Text(text = title, fontSize = 18.sp, color = Color.White)
                }

                IconButton(
                    icon = painterResource(Res.drawable.ic_square_lock),
                    contentDescription = "Cancel Icon",
                    onClick = onDismiss
                )
            }

//            Modal Content
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                content()
            }

//            Modal Footer
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0x001D2430), Color(0xFF1D2430)
                        )
                    )
                ).padding(16.dp),
            ) {
                Button(
                    onClick = secondaryAction.onClick, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.20f),
                        contentColor = Color.White,
                    ),
//                    border = BorderStroke(1.dp, Color(0xFFFFF9F9)),
                    interactionSource = remember { MutableInteractionSource() }) {
                    Text(secondaryAction.name, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = primaryAction.onClick, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xCC15181D),
                        disabledContainerColor = Color.White.copy(alpha = 0.5f),
                        disabledContentColor = Color(0xCC15181D).copy(alpha = 0.60f)
                    ),
                    enabled = primaryAction.enabled
                ) {
                    Text(primaryAction.name, fontWeight = FontWeight.Bold)
                }

            }
        }
    }
}