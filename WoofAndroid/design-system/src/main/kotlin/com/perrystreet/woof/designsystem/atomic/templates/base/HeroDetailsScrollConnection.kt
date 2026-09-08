package com.perrystreet.woof.designsystem.atomic.templates.base

import androidx.compose.animation.core.animate
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity

@Stable
internal class HeroDetailsScrollConnection(
    private val isListAtTop: () -> Boolean,
) : NestedScrollConnection {
    private var minHeightPx by mutableFloatStateOf(0f)
    private var maxHeightPx by mutableFloatStateOf(0f)

    var heightPx by mutableFloatStateOf(0f)
        private set

    val progress: Float
        get() = when (maxHeightPx > minHeightPx) {
            true -> ((heightPx - minHeightPx) / (maxHeightPx - minHeightPx)).coerceIn(0f, 1f)
            false -> 0f
        }

    private val isExpanded: Boolean
        get() = heightPx >= maxHeightPx

    private val isCollapsed: Boolean
        get() = heightPx <= minHeightPx

    fun updateBounds(minHeightPx: Float, maxHeightPx: Float) {
        val wasCollapsed = isCollapsed
        this.minHeightPx = minHeightPx
        this.maxHeightPx = maxHeightPx.coerceAtLeast(minHeightPx)
        heightPx = when (wasCollapsed) {
            true -> minHeightPx
            false -> heightPx.coerceIn(minHeightPx, this.maxHeightPx)
        }
    }

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val delta = available.y
        val isScrollingUp = delta < 0f
        return when (isScrollingUp && !isExpanded) {
            true -> Offset(x = 0f, y = grow(-delta))
            false -> Offset.Zero
        }
    }

    override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
        val delta = available.y
        val isScrollingDown = delta > 0f
        return when (isScrollingDown && !isCollapsed && isListAtTop()) {
            true -> Offset(x = 0f, y = shrink(delta))
            false -> Offset.Zero
        }
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        val isSettled = isExpanded || isCollapsed
        return when (isSettled) {
            true -> Velocity.Zero
            false -> {
                settle(velocity = available.y)
                available
            }
        }
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        val isFlingingDown = available.y > 0f
        return when (isFlingingDown && !isCollapsed && isListAtTop()) {
            true -> {
                settle(velocity = available.y)
                available
            }
            false -> Velocity.Zero
        }
    }

    private fun grow(amount: Float): Float {
        val target = (heightPx + amount).coerceAtMost(maxHeightPx)
        val consumed = heightPx - target
        heightPx = target
        return consumed
    }

    private fun shrink(amount: Float): Float {
        val target = (heightPx - amount).coerceAtLeast(minHeightPx)
        val consumed = heightPx - target
        heightPx = target
        return consumed
    }

    private suspend fun settle(velocity: Float) {
        val expandsByVelocity = velocity < -SettleVelocityThreshold
        val collapsesByVelocity = velocity > SettleVelocityThreshold
        val target = when {
            expandsByVelocity -> maxHeightPx
            collapsesByVelocity -> minHeightPx
            progress >= 0.5f -> maxHeightPx
            else -> minHeightPx
        }
        animate(initialValue = heightPx, targetValue = target, initialVelocity = -velocity) { value, _ ->
            heightPx = value
        }
    }

    private companion object {
        const val SettleVelocityThreshold = 800f
    }
}
