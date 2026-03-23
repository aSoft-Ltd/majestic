package majestic.users.dashboard.roles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.button.appearence.cardButton
import majestic.button.appearence.plusButton
import majestic.button.basic.CardButton
import majestic.button.basic.PlusButton
import majestic.shared.users.dashboard.HeaderColors

data class Labels(
    val title: String,
    val manage: String
)

data class HeaderProps(
    val labels: Labels,
    val colors: HeaderColors
)

@Composable
internal fun Header(
    modifier: Modifier,
    props: HeaderProps,
    add: () -> Unit,
    manage: () -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = props.labels.title,
        color = props.colors.title,
        fontSize = 20.sp,
        minLines = 1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CardButton(
            text = props.labels.manage,
            modifier = Modifier.cardButton(
                color = props.colors.manage,
                onClick = manage
            )
        )

        PlusButton(
            modifier = Modifier.plusButton(
                colors = props.colors.add,
                onClick = add
            ),
        )
    }
}