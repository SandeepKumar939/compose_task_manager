import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.task_manager.components.CircularProgressBar
import com.example.task_manager.components.EmptyStateUI
import com.example.task_manager.components.FilterAndSortOptions
import com.example.task_manager.components.FilterType
import com.example.task_manager.components.ShimmerEffect
import com.example.task_manager.components.SortType
import com.example.task_manager.components.TaskTopAppBar
import com.example.task_manager.vm.TaskViewModel


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val tasks by viewModel.tasks.collectAsState()

    var filterState by remember { mutableStateOf(FilterType.ALL) }
    var sortState by remember { mutableStateOf(SortType.NONE) }

    // Calculate the progress for completed tasks
    val completedTasks = tasks.count { it.isCompleted }
    val totalTasks = tasks.size
    val progress = if (totalTasks > 0) completedTasks.toFloat() / totalTasks.toFloat() else 0f

    LaunchedEffect(viewModel.tasks) {
        viewModel.loadTasks()
    }

    Scaffold(
        topBar = { TaskTopAppBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("task_creation") }) {
                Text("+")  // Replace with an actual Icon if needed
            }
        }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            // Display the circular progress bar showing task completion percentage
            CircularProgressBar(progress = progress)

            // Filter and Sort Options
            FilterAndSortOptions(
                filterState = filterState,
                sortState = sortState,
                onFilterChange = { filterState = it },
                onSortChange = { sortState = it }
            )

            // Show Shimmer Effect while tasks are loading
            if (tasks.isEmpty()) {
                EmptyStateUI()
            } else {
                // Show shimmer if tasks are still loading
                if (tasks.isEmpty()) {
                    ShimmerEffect(modifier = Modifier.fillMaxSize())
                }
                TaskList(
                    tasks = tasks,
                    filterState = filterState,
                    sortState = sortState,
                    onTaskClick = { task -> navController.navigate("task_details/${task.id}") },
                    onTaskComplete = { task -> viewModel.completeTask(task) },
                    onTaskDelete = { task -> viewModel.deleteTask(task) },
                )

            }
        }
    }
}






