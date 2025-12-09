package majestic.users.profile.permissions.detail

import majestic.users.profile.permissions.PermissionsProps
import majestic.users.tools.data.Permissions


internal fun PermissionsProps.toDetailProperties(
    permissions: Permissions
): DetailedProperties = DetailedProperties(
    drawables = DetailedDrawables(
        leadingIcon = permissions.resource,
        rightAngle = rightAngle,
        trailIcon = trailIcon
    ),
    colors = colors.detailed,
    labels = DetailedLabels(
        leadTitle = "Permissions",
        trailingTitle = permissions.title
    ),
    permissions = permissions.permissions
)
