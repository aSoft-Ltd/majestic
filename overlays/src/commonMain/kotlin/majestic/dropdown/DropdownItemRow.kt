package majestic.dropdown

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

// dropdown item layout used by all dropdowns
@Composable
internal fun <T> DropdownItemRow(
    item: DropdownItem<T>,
    colors: DropdownColors,
    isSelected: Boolean,
    isDestructive: Boolean = false,
    trailingIconRes: DrawableResource? = null
) {
    val tint = when {
        isDestructive -> Color(0xFFEF5350)
        isSelected -> colors.itemsSelected
        else -> colors.itemsDefault.copy(0.8f)
    }

    val trailingIconTint = colors.itemsSelected

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (item.leadingIcon !== null) {
            Icon(
                imageVector = item.leadingIcon,
                contentDescription = item.label,
                tint = tint,
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = item.label,
            modifier = Modifier.weight(1f), // pushes trailing icon to the end
            color = tint,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        trailingIconRes?.let { res ->
            Icon(
                painter = painterResource(res),
                contentDescription = null,
                tint = trailingIconTint,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}