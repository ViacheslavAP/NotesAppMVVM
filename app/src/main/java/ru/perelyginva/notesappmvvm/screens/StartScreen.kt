package ru.perelyginva.notesappmvvm.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.perelyginva.notesappmvvm.navigation.NavRoute
import ru.perelyginva.notesappmvvm.ui.theme.NotesAppMVVMTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StartScreen(navController: NavHostController) {

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "What will we use?")
            Button(
                onClick = {
                          navController.navigate(route = NavRoute.MainScreen.route)
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp),
            ) {
                Text(text = "Telephone Database")
            }

            Button(
                onClick = {
                    navController.navigate(route = NavRoute.MainScreen.route)
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp),
            ) {
                Text(text = "Cloud storage")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
    NotesAppMVVMTheme {

        StartScreen(navController = rememberNavController())
    }
}

