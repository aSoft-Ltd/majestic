package majestic.payments.wallet.form

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource

data class PaymentMethod(
    val name: String,
    val account: String,
    val type: String,
    val image: DrawableResource,
) {
    companion object
}

@Composable
internal fun PaymentMethods(
    description: String,
    colors: PaymentCardColors,
    methods: List<PaymentMethod>,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(10.dp)
) {
    Text(
        text = description,
        fontSize = 14.sp,
        lineHeight = 1.sp,
        color = colors.foreground.copy(0.4f)
    )
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth().heightIn(max = 700.dp).padding(vertical = 10.dp),
        columns = GridCells.Fixed(if (orientation is Landscape) 2 else 1),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(methods.size) { index ->
            val item = methods[index]
            val interactionSource = remember { MutableInteractionSource() }
            val isHovered by interactionSource.collectIsHoveredAsState()
            var isSelected by remember { mutableStateOf(false) }
            val background = if (isSelected || isHovered) colors.background else colors.foreground.copy(0.02f)

            PaymentCard(
                modifier = Modifier.fillMaxWidth()
                    .pointerHoverIcon(PointerIcon.Hand)
                    .hoverable(interactionSource = interactionSource)
                    .clip(RoundedCornerShape(8.dp))
                    .background(background)
                    .onClick { isSelected = !isSelected }
                    .padding(10.dp),
                selected = isSelected,
                title = item.account,
                description = if (item.type.isNotEmpty()) "${item.type} - ${item.name}" else item.name,
                image = item.image,
                colors = colors,
            )
        }
    }
}
