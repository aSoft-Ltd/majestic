package majestic.users.labels.general

data class GenderLabels(val male: String, val female: String) {
    companion object {
        val english by lazy {
            GenderLabels(
                male = "Male",
                female = "Female"
            )
        }
        val swahili by lazy {
            GenderLabels(
                male = "Mwanaume",
                female = "Mwanamke"
            )
        }
    }
}
