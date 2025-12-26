package majestic.users.labels.table.actions.reset

data class ResetPromptLabels(
    val title: String,
    val description: String,
    val submit: String
) {
    companion object {
        val english by lazy {
            ResetPromptLabels(
                title = "Reset Succesfully",
                description = "Reset succesfully. Set a password before login",
                submit = "Ok"
            )
        }
        val swahili by lazy {
            ResetPromptLabels(
                title = "Ubadilishaji Nywila Umefanikiwa",
                description = "Ubadilishaji Nywila Umefanikiwa. Weka Nywila kabla ya kuingia",
                submit = "Sawa"
            )
        }
    }
}
