package majestic.payments.transaction.details.tools

import majestic.payments.tools.account.AccountDetail

class TransactionDetail(
    val payer: AccountDetail,
    val recipient: AccountDetail,
    val purpose: String,
    val amount: String
) {
    companion object
}
