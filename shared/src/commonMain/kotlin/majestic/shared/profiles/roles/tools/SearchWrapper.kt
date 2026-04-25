package majestic.shared.profiles.roles.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import majestic.Search
import majestic.SearchColors
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.details.header.RolesHeaderLabels

@Composable
internal fun SearchWrapper(
    modifier: Modifier,
    colors: SearchColors,
    controller: AssignmentController,
    labels: RolesHeaderLabels
) = Search(
    modifier = modifier,
    value = controller.searchQuery,
    onChange = { controller.searchQuery = it },
    hint = labels.search,
    colors = colors,
)