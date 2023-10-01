package com.example.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Stable
class CollapsingScaffoldState(
    val collapsedHeightPx:Int,
    scrollOffset:Float = 0f,
    heightRange: IntRange = IntRange(0, 0)
){
    internal val dragAnimator = Animatable(scrollOffset)

    internal var minHeight = heightRange.first
    internal var maxHeight = heightRange.last
    private val rangeDifference
        get() = maxHeight - minHeight

    val scrollOffset
        get() = dragAnimator.value

    val process:Float
        get(){
            val p = (scrollOffset / rangeDifference).coerceIn(0f, 1f)
            return if (p.isNaN()) 0f else p
        }

    val isMaxOffsetReached by derivedStateOf { if (dragAnimator.value == 0f) false else dragAnimator.value <= (-minHeight.toFloat()) }

    internal suspend fun snapTo(value:Float) {
        val scroll = scrollOffset + value
        val consume = scroll.toInt().coerceIn(rangeDifference, 0)
        dragAnimator.snapTo(consume.toFloat())
    }

    internal fun calcConsumedValue(value:Float):Float{
        val scroll = scrollOffset + value
        val consume = scroll.coerceIn(rangeDifference.toFloat(), 0f)
        val old = scrollOffset
        return consume - old
    }

    internal suspend fun animateTo(velocity:Float, targetOffsetX:Float) {
        val consume = targetOffsetX.coerceIn(rangeDifference.toFloat(), 0f)
        dragAnimator.animateTo(consume, initialVelocity = velocity)
    }

    companion object {
        val Saver = run {

            val minHeightKey = "MinHeight"
            val maxHeightKey = "MaxHeight"
            val scrollOffsetKey = "ScrollOffset"
            val collapsedHeightKey = "CollapsedHeightKey"

            mapSaver(
                save = {
                    mapOf(
                        minHeightKey to it.minHeight,
                        maxHeightKey to it.maxHeight,
                        scrollOffsetKey to it.scrollOffset,
                        collapsedHeightKey to it.collapsedHeightPx
                    )
                },
                restore = {
                    CollapsingScaffoldState(
                        collapsedHeightPx = (it[collapsedHeightKey] as Int),
                        heightRange = (it[minHeightKey] as Int)..(it[maxHeightKey] as Int),
                        scrollOffset = it[scrollOffsetKey] as Float,
                    )
                }
            )
        }
    }
}

@Composable
fun rememberCollapsingScaffoldState(
    minHeight:Dp
): CollapsingScaffoldState {
    val minHeightPx = with(LocalDensity.current){ minHeight.toPx() }.roundToInt()
    return rememberSaveable(saver = CollapsingScaffoldState.Saver){
        CollapsingScaffoldState(collapsedHeightPx = minHeightPx)
    }
}

@Composable
private fun CollapsingScaffoldLayout(
    state:CollapsingScaffoldState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Layout(
        content = content,
        modifier = modifier
    ){ measurables, constraints ->

        require(measurables.size == 2){ "CollapsingScaffoldLayout require 2 composable function: 1 -> scrollable header; 2 -> LazyColumn" }

        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )

        val toolbar = measurables.first().measure(looseConstraints.copy(maxHeight = Constraints.Infinity))

        state.maxHeight = 0
        state.minHeight = toolbar.height - state.collapsedHeightPx

        val scrollContent = measurables.last().measure(
            looseConstraints.copy(
                maxHeight = (constraints.maxHeight - state.collapsedHeightPx)
            )
        )

        layout(width = constraints.maxWidth, height = constraints.maxHeight){
            toolbar.placeRelative(x = 0, y = 0)
            scrollContent.placeRelative(x = 0, y = toolbar.measuredHeight)
        }
    }
}

@Composable
fun CollapsingScaffold(
    modifier: Modifier = Modifier,
    state: CollapsingScaffoldState,
    topBar:@Composable BoxScope.()->Unit,
    content:@Composable BoxScope.()->Unit
){
    val scope = rememberCoroutineScope()

    val decay = splineBasedDecay<Float>(Density(LocalContext.current))
    val dragState = rememberDraggableState(onDelta = { delta ->
        scope.launch {
            state.snapTo(delta)
        }
    })

    CollapsingScaffoldLayout(
        state = state,
        modifier = modifier
            .nestedScroll(
                connection = rememberNestedScrollConnection(state, decay)
            )
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .offset { IntOffset(x = 0, y = state.dragAnimator.value.toInt()) }
                .draggable(
                    state = dragState,
                    Orientation.Vertical,
                    onDragStopped = { velocity ->
                        val targetOffsetX = decay.calculateTargetValue(state.scrollOffset, velocity)
                        scope.launch {
                            state.animateTo(velocity, targetOffsetX)
                        }
                    }
                )
        ){
            topBar()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(x = 0, y = state.scrollOffset.toInt()) }
        ) {
            content()
        }

    }

}

@Composable
private fun rememberNestedScrollConnection(
    state:CollapsingScaffoldState,
    decay: DecayAnimationSpec<Float>
): NestedScrollConnection {

    val scope = rememberCoroutineScope()

    return remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                return if (delta < 0) {
                    val yOffset = state.calcConsumedValue(delta)
                    scope.launch {
                        state.snapTo(delta)
                    }
                    Offset(x = 0f, yOffset)
                } else {
                    Offset.Zero
                }
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val yOffset = state.calcConsumedValue(delta)
                scope.launch {
                    state.snapTo(delta)
                }
                return Offset(x = 0f, yOffset)
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                val isReachedTop = state.scrollOffset == state.minHeight.toFloat()
                return if (available.y < 0 && isReachedTop) {
                    scope.launch {
                        val targetOffsetX = decay.calculateTargetValue(state.scrollOffset, available.y)
                        state.animateTo(available.y, targetOffsetX)
                    }
                    available
                } else {
                    Velocity.Zero
                }
            }

            override suspend fun onPostFling(
                consumed: Velocity,
                available: Velocity
            ): Velocity {
                scope.launch {
                    val targetOffsetX = decay.calculateTargetValue(state.scrollOffset, available.y)
                    state.animateTo(available.y, targetOffsetX)
                }
                return super.onPostFling(consumed, available)
            }
        }
    }
}