package com.example.task_manager.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterAndSortOptions(
    filterState: FilterType,
    sortState: SortType,
    onFilterChange: (FilterType) -> Unit,
    onSortChange: (SortType) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        DropdownMenuButton(
            text = "Filter: ${filterState.name}",
            options = FilterType.values().toList(),
            onSelect = onFilterChange
        )
        Spacer(modifier = Modifier.width(8.dp))
        DropdownMenuButton(
            text = "Sort: ${sortState.name}",
            options = SortType.values().toList(),
            onSelect = onSortChange
        )
    }
}
