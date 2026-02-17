package majestic.payments.tools

import androidx.compose.ui.graphics.Color
import majestic.payments.labels.PaymentStatusLabels

enum class PaymentStatus {
    PAID,
    PARTIAL,
    UNPAID,
    OVERPAID;

    fun getLabel(labels: PaymentStatusLabels) = when (this) {
        PAID -> labels.paid
        PARTIAL -> labels.partial
        UNPAID -> labels.unpaid
        OVERPAID -> labels.overpaid
    }

    fun getColor() = when (this) {
        PAID -> Color(0xFF81C784)
        PARTIAL -> Color(0xFFFBC02D)
        UNPAID -> Color(0xFFE15B5B)
        OVERPAID -> Color(0xFF4FC3F7)
    }
}
