package ru.perelyginva.notesappmvvm.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.perelyginva.notesappmvvm.navigation.NavRoute
import ru.perelyginva.notesappmvvm.ui.theme.NotesAppMVVMTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate(NavRoute.AddScreen.route)
        }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add icons",
                tint = Color.White
            )
        }
    }) {
        Column {
            NoteItem(title = "Note Title", subTitle = "Descriptions", navController = navController)
            NoteItem(title = "Note Title1", subTitle = "Descriptions1", navController = navController)
            NoteItem(title = "Note Title2", subTitle = "Descriptions2", navController = navController)
            NoteItem(title = "Note Title3", subTitle = "Descriptions3", navController = navController)
            NoteItem(title = "Note Title4", subTitle = "Descriptions4", navController = navController)
        }





    }
}

@Composable
fun NoteItem(title: String, subTitle: String, navController: NavHostController){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.NoteScreen.route)
            },
        elevation = 6.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = subTitle,
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

        MainScreen(navController = rememberNavController())
    }
}

