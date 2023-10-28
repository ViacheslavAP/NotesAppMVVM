package ru.perelyginva.notesappmvvm.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.perelyginva.notesappmvvm.R
import ru.perelyginva.notesappmvvm.model.NoteModel
import ru.perelyginva.notesappmvvm.navigation.MainViewModel
import ru.perelyginva.notesappmvvm.navigation.MainViewModelFactory
import ru.perelyginva.notesappmvvm.navigation.NavRoute
import ru.perelyginva.notesappmvvm.ui.theme.NotesAppMVVMTheme

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteScreen(navController: NavHostController, viewModel: MainViewModel, noteId: String?) {

    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    //TODO("разобраться")
    val note = notes.firstOrNull { it.id == noteId?.toInt() } ?: NoteModel(
        title = stringResource(R.string.none),
        subtitle = stringResource(R.string.none)
    )
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    var title by remember { mutableStateOf("Empty") }
    var subTitle by remember { mutableStateOf("Empty") }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 6.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {

                    Text(
                        text = stringResource(R.string.edit_note),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                    )

                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(stringResource(R.string.title)) },
                        isError = title.isEmpty()
                    )

                    OutlinedTextField(
                        value = subTitle,
                        onValueChange = { subTitle = it },
                        label = { Text(stringResource(R.string.subtitle)) },
                        isError = subTitle.isEmpty()
                    )

                    Button(modifier = Modifier
                        .padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(
                                NoteModel(id = note.id, title = title, subtitle = subTitle)
                            ) {
                                navController.navigate(NavRoute.MainScreen.route)
                            }
                        }
                    ) {
                        Text(text = stringResource(R.string.update_note))
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = note.title,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Cursive,
                            modifier = Modifier
                                .padding(23.dp)
                        )

                        Text(
                            text = note.subtitle,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Button(onClick = {
                        coroutineScope.launch {
                            title = note.title
                            subTitle = note.subtitle
                            bottomSheetState.show()
                        }
                    }) {
                        Text(text = stringResource(R.string.update))
                    }

                    Button(onClick = {
                        //delete note
                        viewModel.deleteNote(note = note){
                            navController.navigate(NavRoute.MainScreen.route)
                        }
                    }) {
                        Text(text = stringResource(R.string.delete))
                    }

                    Button(modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth(),
                        onClick = {
                            navController.navigate(NavRoute.MainScreen.route)
                        }) {
                        Text(text = stringResource(R.string.nav_back))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevNoteScreen() {
    NotesAppMVVMTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(
            factory = MainViewModelFactory(context.applicationContext as Application)
        )
        NoteScreen(
            navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1"
        )
    }
}

