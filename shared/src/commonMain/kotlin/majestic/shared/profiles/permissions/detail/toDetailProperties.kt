package majestic.shared.profiles.permissions.detail

import majestic.shared.profiles.Permission


internal fun Permission.toDetailProperties() = DetailedProperties(
    permissions = actions,
    trailingIcon = resource,
    trailingTitle = title
)