package majestic.multiselect.defaults


data class MultiSelectDefaults(
    val dropdown: DropDownDefaults,
    val selected: SelectedDefaults
) {
    companion object {
        val Default = MultiSelectDefaults(
            dropdown = DropDownDefaults.Default,
            selected = SelectedDefaults.Default
        )
    }
}