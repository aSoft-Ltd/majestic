package majestic.payments.transaction.details

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.layouts.Flex
import majestic.layouts.FlexCol
import majestic.layouts.FlexRow
import majestic.payments.labels.transaction.TransactionLabels
import majestic.payments.tools.account.AccountProvider
import majestic.payments.tools.document.FileDetail
import majestic.payments.transaction.details.tools.AccountCard
import majestic.payments.transaction.details.tools.TransactionDetail
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.bank_cheque

private fun Modifier.card(background: Color, orientation: ScreenOrientation) = this
    .clip(RoundedCornerShape(if (orientation is Landscape) 10.dp else 0.dp))
    .background(background)
    .padding(if (orientation is Landscape) 10.dp else 12.dp)

@Composable
fun TransactionInfo(
    labels: TransactionLabels,
    colors: ColorPair,
    orientation: ScreenOrientation,
    detail: TransactionDetail,
    modifier: Modifier = Modifier
) = Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
    Flex(
        modifier = Modifier.fillMaxWidth(),
        orientation = orientation,
        col = FlexCol(arrangement = Arrangement.spacedBy(10.dp)),
        row = FlexRow(arrangement = Arrangement.spacedBy(10.dp))
    ) {
        AccountCard(
            modifier = Modifier.card(colors.background, orientation)
                .then(
                    when (orientation) {
                        is Portrait -> Modifier.fillMaxWidth()
                        is Landscape -> Modifier.weight(1f)
                    }
                ),
            labels = labels,
            title = labels.payerInfo,
            name = detail.payer.name,
            number = detail.payer.number,
            color = colors.foreground,
            provider = AccountProvider(
                name = detail.payer.bank.name,
                image = detail.payer.bank.image
            )
        )
        AccountCard(
            modifier = Modifier.card(colors.background, orientation)
                .then(
                    when (orientation) {
                        is Portrait -> Modifier.fillMaxWidth()
                        is Landscape -> Modifier.weight(1f)
                    }
                ),
            labels = labels,
            title = labels.recipientInfo,
            name = detail.recipient.name,
            number = detail.recipient.number,
            color = colors.foreground,
            provider = AccountProvider(
                name = detail.recipient.bank.name,
                image = detail.recipient.bank.image
            )
        )
    }
    AccountCard(
        modifier = Modifier.fillMaxWidth().card(colors.background, orientation),
        title = labels.purpose,
        description = detail.purpose,
        color = colors.foreground
    )
    AccountCard(
        modifier = Modifier.fillMaxWidth().card(colors.background, orientation),
        title = labels.proof,
        color = colors.foreground
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val isHovered by interactionSource.collectIsHoveredAsState()
        val bgColor = when {
            isHovered -> colors.foreground.copy(alpha = 0.02f)
            else -> Color.Transparent
        }
        FileDetail(
            modifier = Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(3.dp))
                .background(bgColor)
                .pointerHoverIcon(PointerIcon.Hand)
                .hoverable(interactionSource = interactionSource)
                .padding(8.dp),
            name = "payment_receipt_22062024.pdf",
            description = "Payment Receipt",
            thumbnail = Res.drawable.bank_cheque,
            color = colors.foreground
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth().card(colors.background, orientation),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = labels.amount,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = colors.foreground.copy(0.7f)
        )
        Text(
            text = "TZS ${detail.amount}/=",
            fontSize = if (orientation is Landscape) 24.sp else 18.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = colors.foreground
        )
    }
}
