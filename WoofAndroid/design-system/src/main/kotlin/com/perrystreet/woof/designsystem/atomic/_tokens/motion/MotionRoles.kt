@file:Suppress("ktlint:standard:package-name")

package com.perrystreet.woof.designsystem.atomic._tokens.motion

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable

@Immutable
data class MotionRoles(
    val durationShortMs: Int,
    val durationEmphasizedMs: Int,
    val shimmerDurationMs: Int,
    val toastDurationMs: Long,
    val emphasizedEasing: Easing,
) {
    companion object {
        val Default = MotionRoles(
            durationShortMs = 200,
            durationEmphasizedMs = 400,
            shimmerDurationMs = 1_200,
            toastDurationMs = 2_500L,
            emphasizedEasing = CubicBezierEasing(0.2f, 0f, 0f, 1f),
        )
    }
}
