import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import com.example.task_manager.components.FilterType
import com.example.task_manager.components.SortType
import com.example.task_manager.components.SwipeBackground
import com.example.task_manager.components.TaskItem
import com.example.task_manager.room.Task
import kotlinx.coroutines.delay


//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun TaskList(
//    tasks: List<Task>,
//    filterState: FilterType,
//    sortState: SortType,
//    onTaskClick: (Task) -> Unit,
//    onTaskComplete: (Task) -> Unit,
//    onTaskDelete: (Task) -> Unit
//) {
//    // Apply filtering logic based on the filterState
//    val filteredTasks = when (filterState) {
//        FilterType.COMPLETED -> tasks.filter { it.isCompleted }
//        FilterType.PENDING -> tasks.filter { !it.isCompleted }
//        else -> tasks // Show all tasks if filterState is ALL
//    }
//
//    // Apply sorting logic based on the sortState
//    val sortedTasks = filteredTasks.sortedWith { task1, task2 ->
//        when (sortState) {
//            SortType.PRIORITY -> task1.priority.compareTo(task2.priority) // Assuming priority is a comparable property
//            SortType.DUE_DATE -> task1.dueDate.compareTo(task2.dueDate) // Assuming dueDate is a comparable property
//            SortType.ALPHABETICAL -> task1.title.compareTo(task2.title)
//            else -> 0 // No sorting if sortState is NONE
//        }
//    }
//
//    LazyColumn {
//        itemsIndexed(sortedTasks, key = { _, task -> task.id }) { index, task ->
//            val dismissState = rememberDismissState()
//
//            // Handle swipe actions
//            LaunchedEffect(dismissState.currentValue) {
//                when (dismissState.currentValue) {
//                    DismissValue.DismissedToEnd -> {
//                        delay(300)
//
//                        if (!task.isCompleted) {
//                            onTaskComplete(task)
//                        }
//
//                    }
//
//                    DismissValue.DismissedToStart -> {
//                        delay(300)
//                        onTaskDelete(task)
//                    }
//
//                    else -> Unit
//                }
//            }
//
//            SwipeToDismiss(
//                state = dismissState,
//                directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
//                background = { SwipeBackground(dismissState) },
//                dismissContent = {
//                    TaskItem(
//                        task = task,
//                        onClick = { onTaskClick(task) },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .pointerInput(Unit) {
//                                detectTapGestures(
//                                    onLongPress = {
//                                        // Handle long press for haptic feedback
//                                    }
//                                )
//                            }
//                    )
//                }
//            )
//        }
//    }
//}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskList(
    tasks: List<Task>,
    filterState: FilterType,
    sortState: SortType,
    onTaskClick: (Task) -> Unit,
    onTaskComplete: (Task) -> Unit,
    onTaskDelete: (Task) -> Unit
) {
    val context = LocalContext.current
    val filteredTasks = when (filterState) {
        FilterType.COMPLETED -> tasks.filter { it.isCompleted }
        FilterType.PENDING -> tasks.filter { !it.isCompleted }
        else -> tasks
    }

    val sortedTasks = filteredTasks.sortedWith { task1, task2 ->
        when (sortState) {
            SortType.PRIORITY -> task1.priority.compareTo(task2.priority)
            SortType.DUE_DATE -> task1.dueDate.compareTo(task2.dueDate)
            SortType.ALPHABETICAL -> task1.title.compareTo(task2.title)
            else -> 0
        }
    }

    LazyColumn {
        itemsIndexed(sortedTasks, key = { _, task -> task.id }) { index, task ->
            val dismissState = rememberDismissState()

            LaunchedEffect(dismissState.currentValue) {
                when (dismissState.currentValue) {
                    DismissValue.DismissedToEnd -> {
                        if (task.isCompleted) {
                            // Reset swipe action
                            dismissState.snapTo(DismissValue.Default)
                            Toast.makeText(context, "Task already completed!", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            delay(300)
                            onTaskComplete(task)
                        }
                    }

                    DismissValue.DismissedToStart -> {
                        delay(300)
                        onTaskDelete(task)
                    }

                    else -> Unit
                }
            }

            SwipeToDismiss(
                state = dismissState,
                directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                background = { SwipeBackground(dismissState) },
                dismissContent = {
                    TaskItem(
                        task = task,
                        onClick = { onTaskClick(task) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        // Handle long press for haptic feedback
                                    }
                                )
                            }
                    )
                }
            )
        }
    }
}





