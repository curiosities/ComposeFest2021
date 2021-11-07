package com.kata.mylayoutcodlab

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.kata.mylayoutcodlab.ui.theme.MyLayoutCodlabTheme

/*

ConstraintLayout
can help you place composables relative to others on the screen and
is an alternative to using multiple Rows, Columns and Boxes.
ConstraintLayout is useful when implementing larger layouts with more complicated alignment requirements.

Note: In the View system, ConstraintLayout was the recommended way to create large and complex layouts as the flat view hierarchy was better for performance.
However, this is not a concern in Compose, which is able to efficiently handle deep layout hierarchies.

ConstraintLayout in Compose works with a DSL:
    References are created using createRefs() (or createRef()) and each composable in ConstraintLayout needs to have a reference associated.
    Constraints are provided using the constrainAs modifier which takes the reference as a parameter and lets you specify its constraints in the body lambda.
    Constraints are specified using linkTo or other helpful methods.
    parent is an existing reference that can be used to specify constraints towards the ConstraintLayout composable itself.
 */

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {

        // Create references for the composables to constrain
        val (button, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button")
        }

        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text(
            "Text",
            Modifier
                .fillMaxWidth(
                    1f
                )
                .constrainAs(text) {
                    top.linkTo(button.bottom, margin = 16.dp)
                    // Centers Text horizontally in the ConstraintLayout
                    centerHorizontallyTo(parent)
                }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyLayoutCodlabTheme {
        ConstraintLayoutContent()
    }
}

@Composable
fun ConstraintLayoutContent2() {
    ConstraintLayout {
        // Creates references for the three composables
        // in the ConstraintLayout's body
        val (button1, button2, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button 1")
        }

        Text(
            "Text",
            Modifier.constrainAs(text) {
                top.linkTo(button1.bottom, margin = 16.dp)
                centerAround(button1.end)
            }
        )

        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text("Button 2")
        }
    }
}

@Preview
@Composable
private fun Preview2() {
    MyLayoutCodlabTheme {
        ConstraintLayoutContent2()
    }
}

/*

Customizing dimensions
Available Dimension behaviors are:
    preferredWrapContent - the layout is wrap content, subject to the constraints in that dimension.
    wrapContent - the layout is wrap content even if the constraints would not allow it.
    fillToConstraints - the layout will expand to fill the space defined by its constraints in that dimension.
    preferredValue - the layout is a fixed dp value, subject to the constraints in that dimension.
    value - the layout is a fixed dp value, regardless of the constraints in that dimension

 */

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout {
        val text = createRef()

        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(
            "This is a very very very very very very very long text",
            Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)

                // line break in the available space. To achieve this, we can change the width behavior of the text:
                width = Dimension.preferredWrapContent
//                    .atMost(100.dp)
//                    .atLeast(100.dp)
            }
        )
    }
}

@Preview
@Composable
fun LargeConstraintLayoutPreview() {
    MyLayoutCodlabTheme {
        LargeConstraintLayout()
    }
}

/*
Decoupled API

ConstraintLayout in a different way:
    Pass in a ConstraintSet as a parameter to ConstraintLayout.
    Assign references created in the ConstraintSet to composables using the layoutId modifier.

 */

@Preview
@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints: ConstraintSet = decoupledConstraints(
            margin = if (maxWidth < maxHeight) {
                16.dp // Portrait constraints
            } else {
                32.dp // Landscape constraints
            }
        )

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet =
    ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin = margin)
        }
    }
