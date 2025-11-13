package majestic.users.tools.data

data class GenderLabels(
    val male: String,
    val female: String
)

enum class Gender {
    MALE, FEMALE;

    fun getLabels(labels: GenderLabels) = when (this) {
        MALE -> labels.male
        FEMALE -> labels.female
    }
}