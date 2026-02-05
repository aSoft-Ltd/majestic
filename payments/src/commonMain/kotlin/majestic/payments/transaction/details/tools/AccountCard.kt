package majestic.payments.transaction.details.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.payments.labels.transaction.TransactionLabels
import majestic.payments.tools.account.AccountProvider
import majestic.payments.tools.separator

@Composable
internal fun AccountCard(
    labels: TransactionLabels,
    title: String,
    name: String,
    number: String,
    provider: AccountProvider,
    color: Color,
    modifier: Modifier = Modifier
) = Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
    Text(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 5.dp)
            .separator(color = color.copy(0.05f))
            .padding(bottom = 5.dp),
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 1.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = color.copy(0.7f)
    )
    DetailRow(modifier = Modifier.fillMaxWidth(), label = labels.accountName, description = name, color = color)
    DetailRow(modifier = Modifier.fillMaxWidth(), label = labels.accountNo, description = number, color = color)
    DetailRow(modifier = Modifier.fillMaxWidth(), label = labels.bank, description = provider.name, image = provider.image, color = color)
}

@Composable
internal fun AccountCard(
    title: String,
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
    Text(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 5.dp)
            .separator(color = color.copy(0.05f))
            .padding(bottom = 5.dp),
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 1.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = color.copy(0.7f)
    )
    content()
}

@Composable
internal fun AccountCard(
    title: String,
    description: String,
    color: Color,
    modifier: Modifier = Modifier
) = AccountCard(
    title = title,
    color = color,
    modifier = modifier
) {
    Text(
        text = description,
        fontSize = 14.sp,
        color = color
    )
}
