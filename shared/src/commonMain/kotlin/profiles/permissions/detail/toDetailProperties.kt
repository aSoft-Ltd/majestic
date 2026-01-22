package profiles.permissions.detail

import profiles.Permissions


internal fun Permissions.toDetailProperties() = DetailedProperties(
    permissions = permissions,
    trailingIcon = resource,
    trailingTitle = title
)