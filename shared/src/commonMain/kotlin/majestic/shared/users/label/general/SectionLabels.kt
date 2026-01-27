package majestic.shared.users.label.general

data class SectionLabels(
    val label: String,
    val description: String,
    val placeholder: String? = null
)

data class TabSectionLabels<T>(
    val tab: String,
    val content: T
)