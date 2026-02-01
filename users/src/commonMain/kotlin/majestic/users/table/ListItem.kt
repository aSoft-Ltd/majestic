package majestic.users.table

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.shared.users.label.table.StatusLabels
import majestic.shared.users.table.ListItemColors
import majestic.shared.users.tools.UsersData
import org.jetbrains.compose.resources.painterResource

data class ListLabels(
    val role: String,
    val permission: String,
    val status: StatusLabels
)


@Composable
fun ListItem(
    user: UsersData,
    labels: ListLabels,
    colors: ListItemColors,
    modifier: Modifier = Modifier,
    menuAction: @Composable () -> Unit
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
) {
    Row(
        modifier = Modifier.weight(1f),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (user.userAvatar != null) Image(
            modifier = Modifier.size(40.dp).clip(RoundedCornerShape(5.dp)),
            painter = painterResource(user.userAvatar!!),
            contentDescription = null,
        )
        else Box(
            modifier = Modifier
                .size(32.dp)
                .background(color = colors.dominantActual, shape = CircleShape)
                .padding(10.dp)
        ) {
            Text(
                text = user.fullName,
                color = colors.surfaceContra,
                fontSize = 16.sp
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = user.fullName,
                    lineHeight = 1.sp,
                    maxLines = 1,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = colors.surfaceContra
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .background(user.status.getColors().copy(.3f), RoundedCornerShape(3.dp))
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = user.status.getLabels(labels = labels.status),
                        fontSize = 10.sp,
                        lineHeight = 1.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = colors.surfaceContra
                    )
                }
            }
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "${user.rolesCount} ${labels.role}",
                    lineHeight = 1.sp,
                    maxLines = 1,
                    fontSize = 13.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = colors.surfaceContra.copy(.4f)
                )
                Text(
                    text = "${user.permissionsCount} ${labels.permission}",
                    lineHeight = 1.sp,
                    maxLines = 1,
                    fontSize = 13.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = colors.surfaceContra.copy(.4f)
                )
            }
        }
    }

    menuAction()
}