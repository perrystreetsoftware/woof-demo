package com.perrystreet.woof.designsystem.theme

import androidx.compose.runtime.Stable
import com.perrystreet.woof.designsystem.atomic._tokens.alpha.AlphaRoles
import com.perrystreet.woof.designsystem.atomic._tokens.aspectratio.AspectRatioRoles
import com.perrystreet.woof.designsystem.atomic._tokens.colors.Colors
import com.perrystreet.woof.designsystem.atomic._tokens.motion.MotionRoles
import com.perrystreet.woof.designsystem.atomic._tokens.sizing.SizingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.spacing.PaddingRoles
import com.perrystreet.woof.designsystem.atomic._tokens.typography.Typography

@Stable
interface ITheme {
    val name: String
    val isDark: Boolean
    val colors: Colors
    val alpha: AlphaRoles
    val padding: PaddingRoles
    val sizing: SizingRoles
    val typography: Typography
    val motion: MotionRoles
    val aspectRatios: AspectRatioRoles
}
