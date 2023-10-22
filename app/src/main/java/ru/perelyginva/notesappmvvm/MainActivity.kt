package ru.perelyginva.notesappmvvm

import android.app.Application
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.perelyginva.notesappmvvm.navigation.MainViewModel
import ru.perelyginva.notesappmvvm.navigation.MainViewModelFactory
import ru.perelyginva.notesappmvvm.navigation.NotesNavHost
import ru.perelyginva.notesappmvvm.ui.theme.NotesAppMVVMTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppMVVMTheme {

                val context = LocalContext.current
                val mViewModel: MainViewModel = viewModel(
                    factory = MainViewModelFactory(context.applicationContext as Application)
                )

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
                            NotesNavHost(mViewModel)
                        }
                    } )
            }
        }
    }
}

