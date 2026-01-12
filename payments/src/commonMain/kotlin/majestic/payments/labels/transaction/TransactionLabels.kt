package majestic.payments.labels.transaction

import majestic.payments.labels.MenuLabels
import majestic.payments.labels.SectionLabels
import majestic.payments.labels.TableLabels

data class TransactionLabels(
    val emptyList: SectionLabels,
    val transactions: String,
    val menu: MenuLabels,
    val table: TableLabels
) {
    companion object {
        val english by lazy {
            TransactionLabels(
                emptyList = SectionLabels(
                    label = "Transactions",
                    description = "There currently are no transactions available on this page. Please initiate transactions to view them here."
                ),
                transactions = "Transactions",
                menu = MenuLabels.english,
                table = TableLabels.english
            )
        }

        val swahili by lazy {
            TransactionLabels(
                emptyList = SectionLabels(
                    label = "Miamala",
                    description = "Hautakuwa na miamala yoyote kwenye ukurasa huu kwa sasa. Tafadhali anzisha miamala ili uiweze kuona hapa."
                ),
                transactions = "Miamala",
                menu = MenuLabels.swahili,
                table = TableLabels.swahili
            )
        }
    }
}
