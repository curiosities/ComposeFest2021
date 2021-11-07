package com.kata.mylayoutcodlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kata.mylayoutcodlab.ui.theme.MyLayoutCodlabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLayoutCodlabTheme {
                LayoutsCodelab()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LayoutsCodelabPreview() {
    MyLayoutCodlabTheme {
        LayoutsCodelab()
    }
}
