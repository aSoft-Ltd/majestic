package majestic.users.table.header.tools

import users.label.table.TableStatusLabels
import users.label.table.UsersStatusLabels


fun getStatusLabels(labels: TableStatusLabels): UsersStatusLabels = UsersStatusLabels(
    invited = labels.invited,
    active = labels.active,
    declined = labels.declined,
    revoked = labels.revoked
)