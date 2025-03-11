//package com.example.task_manager.components
//
//
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.unit.dp
//import com.example.drag.ListItem
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
//
//@Composable
//fun DraggableLazyList(
//    items: List<ListItem>,
//    onSwap: (Int, Int) -> Unit
//) {
//    var overscrollJob by remember { mutableStateOf<Job?>(null) }
//    val listState = rememberLazyListState()
//    val scope = rememberCoroutineScope()
//    var draggedIndex by remember { mutableStateOf<Int?>(null) }
//
//
//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        state = listState,
//        verticalArrangement = Arrangement.spacedBy(12.dp),
//        contentPadding = PaddingValues(12.dp)
//    ) {
//        items(items.size) { index ->
//            val isDragging = draggedIndex == index
//            val elevation by animateDpAsState(if (isDragging) 16.dp else 4.dp, label = "")
//
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .pointerInput(Unit) {
//                        detectDragGesturesAfterLongPress(
//                            onDragStart = { draggedIndex = index },
//                            onDrag = { change, dragAmount ->
//                                change.consume()
//                                scope.launch {
//                                    val newIndex = (index + dragAmount.y / 100).toInt()
//                                        .coerceIn(0, items.size - 1)
//                                    if (newIndex != index) {
//                                        onSwap(index, newIndex)
//                                        draggedIndex = newIndex
//                                    }
//                                }
//                            },
//                            onDragEnd = { draggedIndex = null },
//                            onDragCancel = { draggedIndex = null }
//                        )
//                    },
//                elevation = CardDefaults.cardElevation(elevation)
//            ) {
//                Box(
//                    modifier = Modifier.padding(16.dp)
//                ) {
//                    Text(text = "${items[index].id}: ${items[index].text}")
//                }
//            }
//        }
//    }
//}
//
//
////data class ListItem(val id: String, val text: String)
