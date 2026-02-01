package majestic.shared.tools.colors

import majestic.ThemeColor
import majestic.shared.profiles.contacts.phone.Backgrounds


fun ThemeColor.toBackgroundsColor(): Backgrounds = Backgrounds(
    portrait = surface.actual.color,
    landscape = toBackground
)