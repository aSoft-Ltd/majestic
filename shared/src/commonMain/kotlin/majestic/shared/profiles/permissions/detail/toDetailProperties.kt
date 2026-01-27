package majestic.shared.profiles.permissions.detail

import majestic.shared.profiles.Permissions


internal fun Permissions.toDetailProperties() = DetailedProperties(
    permissions = permissions,
    trailingIcon = resource,
    trailingTitle = title
)