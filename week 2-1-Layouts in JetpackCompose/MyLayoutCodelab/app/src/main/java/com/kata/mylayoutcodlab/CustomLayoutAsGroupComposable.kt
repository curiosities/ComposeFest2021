package com.kata.mylayoutcodlab

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kata.mylayoutcodlab.ui.theme.MyLayoutCodlabTheme

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    // for a group of composables
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // measure and position children given constraints logic here

        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each child
            measurable.measure(constraints)
        }

        // Track the y co-ord we have placed children up to
        var yPosition = 0

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}

@Preview()
@Composable
fun CustomLayoutPreview() {
    MyLayoutCodlabTheme {
        MyOwnColumn(Modifier.padding(8.dp)) {
            Text("MyOwnColumn")
            Text("places items")
            Text("vertically.")
            Text("We've done it by hand!")
        }
    }
}
