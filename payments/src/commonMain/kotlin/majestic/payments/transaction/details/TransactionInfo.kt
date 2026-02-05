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
import majestic.payments.tools.AccountProvider
import majestic.payments.tools.document.FileDetail
import majestic.payments.transaction.details.tools.AccountCard
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.bank_cheque
import tz.co.asoft.majestic_payments.generated.resources.crdb_logo
import tz.co.asoft.majestic_payments.generated.resources.nmb_logo

private fun Modifier.card(background: Color, orientation: ScreenOrientation) = this
    .clip(RoundedCornerShape(if (orientation is Landscape) 10.dp else 0.dp))
    .background(background)
    .padding(if (orientation is Landscape) 10.dp else 12.dp)

@Composable
fun TransactionInfo(
    colors: ColorPair,
    orientation: ScreenOrientation,
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
            title = "Payer Info",
            name = "Simoni Lissu Moshi",
            number = "1234567890",
            color = colors.foreground,
            provider = AccountProvider(
                name = "CRDB",
                image = Res.drawable.crdb_logo
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
            title = "Recipient Info",
            name = "Kilimo Kijani School",
            number = "1234567890",
            color = colors.foreground,
            provider = AccountProvider(
                name = "NMB",
                image = Res.drawable.nmb_logo
            )
        )
    }
    AccountCard(
        modifier = Modifier.fillMaxWidth().card(colors.background, orientation),
        title = "Purpose",
        description = "Package renewal for Kilimo Kijani School - Academic Year 2023/2024",
        color = colors.foreground
    )
    AccountCard(
        modifier = Modifier.fillMaxWidth().card(colors.background, orientation),
        title = "Proof",
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
            text = "Amount",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = colors.foreground.copy(0.7f)
        )
        Text(
            text = "TZS 220,000.00/=",
            fontSize = if (orientation is Landscape) 24.sp else 18.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = colors.foreground
        )
    }
}
