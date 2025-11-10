//package majestic.users.table
//
//import academia.generated.resources.Res
//import academia.generated.resources.ic_user_edit
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.input.pointer.PointerIcon
//import androidx.compose.ui.input.pointer.pointerHoverIcon
//import androidx.compose.ui.unit.dp
//import majestic.ColorPair
//import majestic.ThemeColor
//import majestic.tooling.onClick
//import tz.co.asoft.academia.admissions.applicant.profile.tools.header.forms.tools.years
//import tz.co.asoft.academia.admissions.dashboard.stickybar.StickyButton
//import tz.co.asoft.academia.admissions.dashboard.stickybar.toStickyBarColors
//import tz.co.asoft.academia.tools.colors.toPopCompColors
//import tz.co.asoft.academia.tools.colors.toPopMainColors
//import tz.co.asoft.academia.tools.filters.FilterByYear
//import tz.co.asoft.academia.tools.filters.FilterDefault
//import tz.co.asoft.academia.tools.filters.SelectFilterColors
//
//@Composable
//internal fun TableActionBar(theme: ThemeColor, hovered: Boolean, onHovered: (Boolean) -> Unit) {
//    StickyButton(
//        modifier = Modifier
//            .background(
//                color = theme.toStickyBarColors(hovered).background,
//                shape = RoundedCornerShape(20.dp)
//            )
//            .pointerHoverIcon(PointerIcon.Hand)
//            .onClick(callback = { })
//            .padding(horizontal = 16.dp),
//        leadingIcon = Res.drawable.ic_user_edit,
//        label = "Enroll",
//        colors = theme.toStickyBarColors(hovered),
//        onHovered = onHovered
//    )
//
//
//    FilterByYear(
//        years = years,
//        selected = "2025",
//        modifier = Modifier.width(120.dp),
//        defaults = FilterDefault(
//            drawerColor = theme.toPopCompColors().background,
//            colors = SelectFilterColors(
//                background = theme.toStickyBarColors(false).background,
//                text = theme.toPopMainColors().foreground,
//                icon = theme.toPopMainColors().foreground,
//            ),
//            containerShape = RoundedCornerShape(8.dp),
//            drawerShape = RoundedCornerShape(8.dp),
//            item = ColorPair(
//                foreground = theme.toPopMainColors().foreground.copy(.8f),
//                background = theme.toPopMainColors().foreground
//            )
//        )
//    )
//}