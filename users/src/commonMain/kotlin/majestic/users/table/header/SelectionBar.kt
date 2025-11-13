package majestic.users.table.header

//import academia.generated.resources.Res
//import academia.generated.resources.ic_cancel
//import academia.generated.resources.ic_user_check
//import academia.generated.resources.ic_user_remove
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.PointerIcon
//import androidx.compose.ui.input.pointer.pointerHoverIcon
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import majestic.ThemeColor
//import majestic.tooling.onClick
//import tz.co.asoft.academia.admissions.dashboard.stickybar.StickyButton
//import tz.co.asoft.academia.admissions.dashboard.stickybar.toStickyBarColors
//
//@Composable
//internal fun SelectionBar(
//    modifier: Modifier,
//    theme: ThemeColor,
//    selectedCount: Int,
//    label: String
//) = Row(
//    modifier = modifier,
//    horizontalArrangement = Arrangement.SpaceBetween,
//    verticalAlignment = Alignment.CenterVertically
//) {
//    Text(
//        text = "$selectedCount $label",
//        color = theme.surface.contra.color,
//        fontWeight = FontWeight.SemiBold
//    )
//    Row(
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//        StickyButton(
//            modifier = Modifier
//                .size(40.dp)
//                .background(
//                    color = theme.toStickyBarColors(false).background,
//                    shape = CircleShape
//                )
//                .pointerHoverIcon(PointerIcon.Hand)
//                .onClick(callback = { }),
//            leadingIcon = Res.drawable.ic_user_check,
//            label = "",
//            colors = theme.toStickyBarColors(false),
//            onHovered = { }
//        )
//        StickyButton(
//            modifier = Modifier
//                .size(40.dp)
//                .background(
//                    color = theme.toStickyBarColors(false).background,
//                    shape = CircleShape
//                )
//                .pointerHoverIcon(PointerIcon.Hand)
//                .onClick(callback = { }),
//            leadingIcon = Res.drawable.ic_user_remove,
//            label = "",
//            colors = theme.toStickyBarColors(false),
//            onHovered = { }
//        )
//        StickyButton(
//            modifier = Modifier
//                .size(40.dp)
//                .background(
//                    color = Color.Transparent,
//                    shape = CircleShape
//                )
//                .pointerHoverIcon(PointerIcon.Hand)
//                .onClick(callback = { }),
//            leadingIcon = Res.drawable.ic_cancel,
//            label = "",
//            colors = theme.toStickyBarColors(false),
//            onHovered = { }
//        )
//    }
//}