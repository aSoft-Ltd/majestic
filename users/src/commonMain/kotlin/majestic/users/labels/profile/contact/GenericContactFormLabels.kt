package majestic.users.labels.profile.contact

data class GenericContactFormLabels(
    val add: DedicatedFormLabels,
    val edit: DedicatedFormLabels,
    val dup: DedicatedFormLabels,
    val delete: ContactDeleteFormLabels,
    val verify: ContactVerificationFormLabels
) {
    companion object {
        fun english(context: String) = GenericContactFormLabels(
            add = DedicatedFormLabels.english("Add", context),
            dup = DedicatedFormLabels.english("Duplicate", context),
            edit = DedicatedFormLabels.english("Edit", context),
            delete = ContactDeleteFormLabels(
                title = "Delete $context",
                submit = "Delete",
                cancel = "Cancel",
                description = "Are you sure you want to delete this $context?",
                info = "$context will be deleted permanently"
            ),
            verify = ContactVerificationFormLabels(
                title = "Verification",
                sentCode = "We sent a verification code to",
                enterCode = "Please enter the code to proceed.",
                submit = "Verify",
                resendCode = "Resend Code",
                changeEmail = "Change $context",
            )
        )

        fun swahili(context: String) = GenericContactFormLabels(
            add = DedicatedFormLabels.swahili("Ongeza", context),
            dup = DedicatedFormLabels.swahili("Nakili", context),
            edit = DedicatedFormLabels.swahili("Badili", context),
            delete = ContactDeleteFormLabels(
                title = "Futa $context",
                submit = "Futa",
                cancel = "Ghairi",
                description = "Unauhakika unataka kufuta $context hii?",
                info = "$context hii itafutwa kabisa"
            ),
            verify = ContactVerificationFormLabels(
                title = "Uthibitishaji",
                sentCode = "Tumetuma nambari ya kuthibitisha kwa",
                enterCode = "Tafadhali weka msimbo ili kuendelea.",
                submit = "Thibitisha",
                resendCode = "Tuma tena Msimbo",
                changeEmail = "Badilisha ${context.lowercase()}",
            )
        )
    }
}
