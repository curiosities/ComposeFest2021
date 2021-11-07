package com.kata.mylayoutcodlab

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun slotApi() {

    /*
    Slot APIs are a pattern Compose introduces to bring in a layer of customization on top of composables
    
    can try and add a parameter for each individual element you could customize, but that quickly gets out of hand:
    Button(
        text = "Button",
        icon: Icon? = myIcon,
        textStyle = TextStyle(...),
        spacingBetweenIconAndText = 4.dp,
    )

    Therefore, instead of adding multiple parameters to customize the component in a way we cannot expect, we added Slots.
    Slots leave an empty space in the UI for the developer to fill as they wish.
    */

    Button(
        modifier = Modifier,
        onClick = { /* Do something */ }
    ) {
        Row {
            Spacer(Modifier.width(4.dp))
            Text("Button")
        }
    }
}
