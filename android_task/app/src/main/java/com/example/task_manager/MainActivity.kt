package com.example.task_manager

import HomeScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.task_manager.ui.screens.TaskCreationScreen
import com.example.task_manager.ui.screens.TaskDetailsScreen
import com.example.task_manager.ui.theme.Task_managerTheme
import com.example.task_manager.vm.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task_managerTheme {
                val navController = rememberNavController()
                val viewModel = androidx.hilt.navigation.compose.hiltViewModel<TaskViewModel>()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        AppNavHost(
                            navController = navController,
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable("task_creation") {
            TaskCreationScreen(viewModel = viewModel, navController = navController)
        }
        composable("task_details/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            if (taskId != null) {
                TaskDetailsScreen(
                    viewModel = viewModel,
                    taskId = taskId,
                    navController = navController
                )
            }
        }
    }
}

