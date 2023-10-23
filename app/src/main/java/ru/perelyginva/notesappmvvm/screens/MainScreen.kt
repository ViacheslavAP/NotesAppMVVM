package ru.perelyginva.notesappmvvm.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.perelyginva.notesappmvvm.R
import ru.perelyginva.notesappmvvm.model.NoteModel
import ru.perelyginva.notesappmvvm.navigation.MainViewModel
import ru.perelyginva.notesappmvvm.navigation.MainViewModelFactory
import ru.perelyginva.notesappmvvm.navigation.NavRoute
import ru.perelyginva.notesappmvvm.ui.theme.NotesAppMVVMTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate(NavRoute.AddScreen.route)
        }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.add_icons),
                tint = Color.White
            )
        }
    }) {

        LazyColumn {
            items(notes) { note ->
                NoteItem(noteModel = note, navController = navController)

            }
        }
    }
}

@Composable
fun NoteItem(noteModel: NoteModel, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(
                    NavRoute.NoteScreen.route + "/${noteModel.id}")
            },
        elevation = 6.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = noteModel.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = noteModel.subtitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,

                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevMainScreen() {
    NotesAppMVVMTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(
            factory = MainViewModelFactory(context.applicationContext as Application)
        )
        MainScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}

