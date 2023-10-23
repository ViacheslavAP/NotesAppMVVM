package ru.perelyginva.notesappmvvm.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun AddScreen(navController: NavHostController, viewModel: MainViewModel) {

    var title by remember { mutableStateOf("") }
    var subTitle by remember { mutableStateOf("") }
    var isButtonEnabled by remember {mutableStateOf(false)}

    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(R.string.add_new_note),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    isButtonEnabled = title.isNotEmpty() && subTitle.isNotEmpty()},
                label = { Text(text = stringResource(R.string.note_title)) },
                isError = title.isEmpty()//если поле пустое, то поле будет красным
            )

            OutlinedTextField(
                value = subTitle,
                onValueChange = {
                    subTitle = it
                    isButtonEnabled = title.isNotEmpty() && subTitle.isNotEmpty()},
                label = { Text(text = stringResource(R.string.note_subtitle)) },
                isError = subTitle.isEmpty()//если поле пустое, то поле будет красным
            )

            Button(modifier = Modifier
                .padding(top = 16.dp),
                enabled = isButtonEnabled,
                onClick = {
                    viewModel.addNote(note = NoteModel(title = title, subtitle = subTitle)) {
                        navController.navigate(NavRoute.MainScreen.route)
                    }
                }) {
                Text(text = stringResource(R.string.add_note))

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun prevAddScreen() {
    NotesAppMVVMTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(
            factory = MainViewModelFactory(context.applicationContext as Application)
        )
        AddScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}

