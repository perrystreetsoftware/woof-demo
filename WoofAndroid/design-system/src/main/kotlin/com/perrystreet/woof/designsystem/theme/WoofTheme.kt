package com.perrystreet.woof.designsystem.theme

import androidx.compose.runtime.Stable
import com.perrystreet.woof.designsystem.atomic._tokens.alpha.AlphaRoles
import com.perrystreet.woof.designsystem.atomic._tokens.aspectratio.AspectRatioRoles
import com.perrystreet.woof.designsystem.atomic._tokens.colors.ColorRoles
import com.perrystreet.woof.designsystem.atomic._tokens.colors.Colors
import com.perrystreet.woof.designsystem.atomic._tokens.motion.MotionRoles
import com.perrystreet.woof.designsystem.atomic._tokens.radius.RadiusRoles
import com.perrystreet.woof.designsystem.atomic._tokens.typography.Typography
import com.perrystreet.woof.designsystem.atomic._tokens.typography.TypographyRoles

@Stable
class WoofTheme private constructor(
    override val name: String,
    override val isDark: Boolean,
    override val colors: Colors,
) : ITheme {
    override val alpha: AlphaRoles = AlphaRoles.Default
    override val radius: RadiusRoles = RadiusRoles.Default
    override val typography: Typography = TypographyRoles.Default
    override val motion: MotionRoles = MotionRoles.Default
    override val aspectRatios: AspectRatioRoles = AspectRatioRoles.Default

    companion object {
        fun light(): ITheme = WoofTheme(name = "Light", isDark = false, colors = ColorRoles.Light)

        fun dark(): ITheme = WoofTheme(name = "Dark", isDark = true, colors = ColorRoles.Dark)
    }
}
