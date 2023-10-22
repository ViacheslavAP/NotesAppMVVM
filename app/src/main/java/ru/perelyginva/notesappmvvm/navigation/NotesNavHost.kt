package ru.perelyginva.notesappmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.perelyginva.notesappmvvm.screens.AddScreen
import ru.perelyginva.notesappmvvm.screens.MainScreen
import ru.perelyginva.notesappmvvm.screens.NoteScreen
import ru.perelyginva.notesappmvvm.screens.StartScreen

/** создали ссылки на экраны и маршруты*/

sealed class NavRoute(val route: String) {
    object StartScreen : NavRoute("start_screen")
    object MainScreen : NavRoute("main_screen")
    object AddScreen : NavRoute("add_screen")
    object NoteScreen : NavRoute("note_screen")
}


@Composable
fun NotesNavHost(mViewModel: MainViewModel) {
    //проинициализируем navController
    val navController = rememberNavController()
    //создаем NavHost
    NavHost(
        navController = navController,
        startDestination = NavRoute.StartScreen.route
    ) {
        //реализуем навигацию
        composable(NavRoute.StartScreen.route) {
            StartScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.MainScreen.route) {
            MainScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.AddScreen.route) {
            AddScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
        composable(NavRoute.NoteScreen.route) {
            NoteScreen(
                navController = navController,
                viewModel = mViewModel
            )
        }
    }
}