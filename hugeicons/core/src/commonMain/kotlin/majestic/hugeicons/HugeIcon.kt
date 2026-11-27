package majestic.hugeicons

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import majestic.hugeicons.bulk.allDrawableResources
import majestic.hugeicons.duotone.allDrawableResources
import majestic.hugeicons.solid.rounded.allDrawableResources
import majestic.hugeicons.solid.sharp.allDrawableResources
import majestic.hugeicons.solid.standard.allDrawableResources
import majestic.hugeicons.stroke.rounded.allDrawableResources
import majestic.hugeicons.stroke.sharp.allDrawableResources
import majestic.hugeicons.stroke.standard.allDrawableResources
import majestic.hugeicons.twotone.allDrawableResources
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import majestic.hugeicons.bulk.Res as BulkRes
import majestic.hugeicons.duotone.Res as DuotoneRes
import majestic.hugeicons.solid.rounded.Res as SolidRoundedRes
import majestic.hugeicons.solid.sharp.Res as SolidSharpRes
import majestic.hugeicons.solid.standard.Res as SolidStandardRes
import majestic.hugeicons.stroke.rounded.Res as StrokeRoundedRes
import majestic.hugeicons.stroke.sharp.Res as StrokeSharpRes
import majestic.hugeicons.stroke.standard.Res as StrokeStandardRes
import majestic.hugeicons.twotone.Res as TwotoneRes

@Composable
fun HugeIcon(
    name: HugeIconName,
    style: HugeIconStyle = HugeIconStyle.Stroke.Standard,
    tint: Color = LocalContentColor.current,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) = Icon(
    imageVector = vectorResource(style.resources().getValue(name.key)),
    contentDescription = contentDescription,
    tint = tint,
    modifier = modifier
)

private fun HugeIconStyle.resources(): Map<String, DrawableResource> = when (this) {
    is HugeIconStyle.Stroke -> when (this) {
        HugeIconStyle.Stroke.Standard -> StrokeStandardRes.allDrawableResources
        HugeIconStyle.Stroke.Rounded -> StrokeRoundedRes.allDrawableResources
        HugeIconStyle.Stroke.Sharp -> StrokeSharpRes.allDrawableResources
    }

    is HugeIconStyle.Solid -> when (this) {
        HugeIconStyle.Solid.Standard -> SolidStandardRes.allDrawableResources
        HugeIconStyle.Solid.Rounded -> SolidRoundedRes.allDrawableResources
        HugeIconStyle.Solid.Sharp -> SolidSharpRes.allDrawableResources
    }

    HugeIconStyle.Bulk -> BulkRes.allDrawableResources
    HugeIconStyle.Duotone -> DuotoneRes.allDrawableResources
    HugeIconStyle.Twotone -> TwotoneRes.allDrawableResources
}