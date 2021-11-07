package com.kata.mylayoutcodlab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kata.mylayoutcodlab.ui.theme.MyLayoutCodlabTheme

/*
Modifiers allow you to decorate a composable.
You can change its behavior, appearance, add information like accessibility labels,
process user input or even add high-level interactions like making something clickable, scrollable, draggable or zoomable.
Modifiers are regular Kotlin objects. You can assign them to variables and reuse them.
You can also chain several modifiers one after the other to compose them.

Modifiers can play a similar role to XML attributes in the View system,
but the type safety of scope-specific modifiers helps you to discover and understand what is available and applicable to a certain layout.
Compare this with XML layouts where it was not always clear if a layout attribute was applicable to a given View.

Order of modifiers matter
Note: By convention, the modifier is specified as the first optional parameter of a function.
This enables you to specify a modifier on a composable without having to name all parameters.

Be mindful when chaining modifiers as the order matters.
As they're concatenated into a single argument, the order affects the final result.

Note: The explicit order helps you to reason about how different modifiers will interact.
Compare this to the View system where you had to learn the box model;
that margins applied "outside" the element but padding "inside" it and a background element would be sized accordingly.
The modifier design makes this behavior explicit and predictable, and gives you more control to achieve the exact behavior you want.
*/

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = { /* Ignoring onClick */ })
            .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image goes here
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            // LocalContentAlpha is defining opacity level of its children
            // CompositionLocalProvider It allows us to pass data implicitly through the composition tree
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun PhotographerCardPreview() {
    MyLayoutCodlabTheme {
        PhotographerCard()
    }
}
