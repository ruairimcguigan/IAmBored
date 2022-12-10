package i.am.bored.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import i.am.bored.ui.theme.BoredomBusterTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoredomBusterTheme {
                IAmBoredScaffold(modifier = Modifier.fillMaxSize())
            }
        }
    }
}