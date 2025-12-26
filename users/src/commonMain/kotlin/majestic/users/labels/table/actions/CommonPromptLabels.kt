package majestic.users.labels.table.actions

data class CommonPromptLabels(
    val waiting: String,
    val success: String,
    val fail: String
) {
    companion object {
        val english by lazy {
            CommonPromptLabels(
                waiting = "Please wait while we are completing your request....",
                success = "Your request has been successfully completed",
                fail = "Unfortunately, your request has not been completed"
            )
        }
        val swahili by lazy {
            CommonPromptLabels(
                waiting = "Tafadhali subiri wakati tunakamilisha ombi lako....",
                success = "Ombi lako limekamilishwa kwa mafanikio",
                fail = "Kwa bahati mbaya, ombi lako halijakamilishwa"
            )
        }
    }
}