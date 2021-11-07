package com.kata.mylayoutcodlab

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kata.mylayoutcodlab.ui.theme.MyLayoutCodlabTheme

/*
should only measure children once
- measuring children twice throws a runtime exception
However, there are times when you need some information about your children before measuring them.

Intrinsics lets you query children before they're actually measured.

To a composable, you can ask for its intrinsicWidth or intrinsicHeight:
    (min|max)IntrinsicWidth: Given this height, what's the minimum/maximum width you can paint your content properly.
    (min|max)IntrinsicHeight: Given this width, what's the minimum/maximum height you can paint your content properly.


 */

@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    /*
    Row measures each child individually and
    the height of Text cannot be used to constraint the Divider.
     */
//    Row(modifier = modifier) {

    /*
    Divider to fill the available space with a given height.
    Sizes its children being forced to be as tall as their minimum intrinsic height
    minIntrinsicHeight will be the maximum minIntrinsicHeight of its children
     */
    Row(modifier = modifier.height(IntrinsicSize.Min)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start),
            text = text1
        )

        Divider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End),

            text = text2
        )
    }
}

@Preview
@Composable
fun TwoTextsPreview() {
    MyLayoutCodlabTheme {
        Surface {
            TwoTexts(text1 = "Hi", text2 = "there")
        }
    }
}
