package majestic.users.table.tools.data

import majestic.ColorPair
import majestic.ThemeColor
import majestic.icons.Res
import majestic.icons.ic_archive
import majestic.icons.ic_assign
import majestic.icons.ic_cancel
import majestic.icons.ic_delete
import majestic.icons.ic_export
import majestic.icons.ic_send_email
import majestic.users.table.header.Action
import majestic.users.table.header.Archive
import majestic.users.table.header.Assign
import majestic.users.table.header.CallBack
import majestic.users.table.header.Cancel
import majestic.users.table.header.Delete
import majestic.users.table.header.Export
import majestic.users.table.header.SelectionBarColors
import majestic.users.table.header.SelectionBarProperties
import majestic.users.table.header.SendMail
import majestic.users.tools.MenuAction
import majestic.users.tools.MenuActionLabels
import majestic.users.tools.data.UsersData
import menu.OptionMenu
import symphony.Table
import users.label.table.TableLabels

internal fun getMenuActionLabels(labels: TableLabels) = MenuActionLabels(
    view = labels.body.actions.view,
    block = labels.body.actions.block.action,
    reset = labels.body.actions.reset.action,
    delete = labels.body.actions.delete.action
)

internal fun getOptions(labels: TableLabels): List<OptionMenu<MenuAction>> = listOf(
    OptionMenu(
        MenuAction.View.getLabel(getMenuActionLabels(labels)),
        MenuAction.View
    ),
    OptionMenu(
        MenuAction.Block.getLabel(getMenuActionLabels(labels)),
        MenuAction.Block
    ),
    OptionMenu(
        MenuAction.Reset.getLabel(getMenuActionLabels(labels)),
        MenuAction.Reset
    ),
    OptionMenu(
        MenuAction.Delete.getLabel(getMenuActionLabels(labels)),
        MenuAction.Delete
    )
)

internal fun Table<UsersData>.toActions(): (CallBack) -> Unit = {
    when (it) {
        Delete -> {}
        SendMail -> {}
        Export -> {}
        Archive -> {}
        Cancel -> {
            selector.unSelectAllItemsInTheCurrentPage()
        }

        Assign -> {}
    }
}

internal fun ThemeColor.getSelectionProps(
    selectCount: Int,
    labels: TableLabels,
) = SelectionBarProperties(
    count = selectCount,
    label = labels.head.selected,
    colors = SelectionBarColors(
        counter = surface.contra.color,
        title = surface.contra.color,
        icons = ColorPair(
            foreground = surface.contra.color,
            background = surface.actual.color,
        )
    ),
    iconBackground = true,
    actions = getActions(labels)
)

internal fun getActions(labels: TableLabels): List<Action> = listOf(
    Action(
        icon = Res.drawable.ic_delete,
        label = labels.head.actions.delete,
        callback = Delete
    ),
    Action(
        icon = Res.drawable.ic_send_email,
        label = labels.head.actions.send,
        callback = SendMail
    ),
    Action(
        icon = Res.drawable.ic_export,
        label = labels.head.actions.export,
        callback = Export
    ),
    Action(
        icon = Res.drawable.ic_archive,
        label = labels.head.actions.archive,
        callback = Archive
    ),
    Action(
        icon = Res.drawable.ic_assign,
        label = labels.head.actions.assign,
        callback = Assign
    ),
    Action(
        icon = Res.drawable.ic_cancel,
        label = labels.head.actions.cancel,
        callback = Cancel
    )
)