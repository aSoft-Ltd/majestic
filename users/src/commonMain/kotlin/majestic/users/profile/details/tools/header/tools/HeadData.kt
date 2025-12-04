package majestic.users.profile.details.tools.header.tools

import org.jetbrains.compose.resources.DrawableResource

data class HeadData(
    val avatar: DrawableResource?,
    val flag: DrawableResource,
    val name: String,
    val gender: String,
    val list: List<FlowItemData>
)