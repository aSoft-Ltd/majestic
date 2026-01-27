package majestic.users.profile.header.tools

import majestic.shared.users.label.profile.ProfileLabels
import majestic.users.tools.data.UsersData


internal fun UsersData.toProfileData(
    labels: ProfileLabels
): List<FlowItemData> = listOf(
    FlowItemData(
        icon = dateJoined.second,
        title = labels.header.joined,
        description = dateJoined.first
    ),
    FlowItemData(
        icon = phone.second,
        title = labels.header.phone,
        description = phone.first
    ),
    FlowItemData(
        icon = lastActive.second,
        title = labels.header.lastActive,
        description = lastActive.first
    ),
    FlowItemData(
        icon = dob.second,
        title = labels.header.dob,
        description = dob.first
    )
)