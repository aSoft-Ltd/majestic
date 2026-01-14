package majestic.users.labels.profile.contact

data class GenericContactFormLabels(
    val add: DedicatedFormLabels,
    val edit: DedicatedFormLabels,
    val dup: ContactPromptFormLabels,
    val delete: ContactPromptFormLabels,
    val verify: ContactVerificationFormLabels
) {
    companion object {
        fun english(context: String) = GenericContactFormLabels(
            add = DedicatedFormLabels.english("Add", context),
            dup = ContactPromptFormLabels(
                title = "Duplicate $context",
                submit = "Duplicate",
                cancel = "Cancel",
                description = "Are you sure you want to duplicate this $context?",
                info = "$context will be duplicated permanently"
            ),
            edit = DedicatedFormLabels.english("Edit", context),
            delete = ContactPromptFormLabels(
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
                changeContact = "Change $context",
            )
        )

        fun swahili(context: String) = GenericContactFormLabels(
            add = DedicatedFormLabels.swahili("Ongeza", context),
            dup = ContactPromptFormLabels(
                title = "Rudufisha $context",
                submit = "Rudufisha",
                cancel = "Ghairi",
                description = "Una uhakika unataka kurudufisha $context hii?",
                info = "$context hii itarudufishwa"
            ),
            edit = DedicatedFormLabels.swahili("Badili", context),
            delete = ContactPromptFormLabels(
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
                changeContact = "Badilisha ${context.lowercase()}",
            )
        )
    }
}
