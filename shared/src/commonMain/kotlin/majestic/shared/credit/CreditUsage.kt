package majestic.shared.credit

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

enum class ItemType {
    Results,
    Exceptional,
    Excellence,
    Exams,
    Elite
}

data class CreditUsage(
    val id: String,
    val logo: DrawableResource,
    val bank: DrawableResource,
    val recipientLogo: DrawableResource,
    val recipientName: String,
    val recipientId: String,
    val payerLogo: DrawableResource,
    val payerName: String,
    val payerId: String,
    val invoiceRef: String,
    val txnRef: String,
    val itemName: String,
    var itemType: ItemType = ItemType.Results,
    val itemColor: Color,
    val used: Int,
    val total: Int,
    val date: String,
    val amount: String,
) {
    companion object
}