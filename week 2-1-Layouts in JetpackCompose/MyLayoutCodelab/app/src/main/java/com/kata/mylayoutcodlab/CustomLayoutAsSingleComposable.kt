package com.kata.mylayoutcodlab

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kata.mylayoutcodlab.ui.theme.MyLayoutCodlabTheme

/*
Principles of layouts in Compose

Some composable functions emit a piece of UI when invoked that is added to a UI tree that will get rendered on the screen.
Each emission (or element) has one parent and potentially many children.
Also, it has a location within its parent: an (x, y) position, and a size: a width and height.

Elements are asked to measure themselves with Constraints that should be satisfied.
Constraints restrict the minimum and maximum width and height of an element.
If an element has child elements it may measure each of them to help determine its own size.
Once an element reports its own size, it has an opportunity to place its child elements relative to itself.

Compose UI does not permit multi-pass measurement.
This means that a layout element may not measure any of its children more than once in order to try different measurement configurations.
Single-pass measurement is good for performance, allowing Compose to handle efficiently deep UI trees.
If a layout element measured its child twice and that child measured one of its children twice and so on,
a single attempt to lay out a whole UI would have to do a lot of work, making it hard to keep your app performing well.
However, there are times when you really need additional information on top of what a single child measurement would tell you
- for these cases there are ways of doing this, we will talk about them later.
 */
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp,
) = this.then(
    layout { measurable, constraints ->
        //
        val placeable = measurable.measure(constraints)

        // Check the composable has a first baseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        // calculate its size and specify it 
        layout(placeable.width, height) {
            // Where the composable gets placed
            // position the composable on the screen
            // If don't, the composable won't be visible
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    MyLayoutCodlabTheme {
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
    }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    MyLayoutCodlabTheme {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}
