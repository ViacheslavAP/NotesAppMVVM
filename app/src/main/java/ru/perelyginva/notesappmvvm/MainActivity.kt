package ru.perelyginva.notesappmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import ru.perelyginva.notesappmvvm.navigation.NotesNavHost
import ru.perelyginva.notesappmvvm.ui.theme.NotesAppMVVMTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppMVVMTheme {

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Notes App")
                            },
                            contentColor = Color.White,
                            elevation = 12.dp
                        )
                    },
                    content = {padding ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding),

                            color = MaterialTheme.colors.background
                        ) {
                            NotesNavHost()
                        }
                    } )
            }
        }
    }
}

