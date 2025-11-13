package majestic.users.table.header.tools.filters

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.Search
import majestic.SearchDefaultColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

internal fun Modifier.searchFilter(color: Color) = this
    .fillMaxWidth()
    .height(34.dp)
    .border(1.dp, color, RoundedCornerShape(8.dp))

data class SearchFilterColors(
    val search: SearchDefaultColors,
    val pop: ColorPair
)

@Composable
internal fun SearchFilter(
    hint: String,
    colors: SearchFilterColors,
    icon: DrawableResource,
    onChange: (String) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    var search by remember { mutableStateOf("") }
    val searchColors = colors.search
    val popColors = colors.pop

    Search(
        colors = searchColors,
        value = search,
        onChange = {
            search = it
            onChange(it)
        },
        focusRequester = focusRequester,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        hint = hint,
        placeholder = {
            Text(text = it ?: "Search", fontSize = 12.sp, color = searchColors.hint)
        },
        textStyle = LocalTextStyle.current.copy(
            fontSize = 14.sp,
            color = searchColors.text
        ),
        icon = {
            Icon(
                modifier = Modifier.size(12.dp),
                painter = painterResource(icon),
                tint = popColors.foreground,
                contentDescription = "search icon"
            )
        }
    )
}
