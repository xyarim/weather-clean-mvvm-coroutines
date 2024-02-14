package com.xyarim.weather.weather.current.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.xyarim.weather.ui.theme.keyLine2
import com.xyarim.weather.ui.theme.keyLine3

@Composable
fun WeatherFilterOptions(
    dateOptions: List<FilterOption>,
    selected: FilterOption,
    onSelectedChange: (FilterOption) -> Unit
) {
    LazyRow(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(keyLine3),
        horizontalArrangement = Arrangement.spacedBy(keyLine3)
    ) {
        items(
            items = dateOptions,
            key = { item -> "${item.days}${item.label}" }
        ) { dateOption ->
            DateOptionItem(
                dateOption = dateOption,
                isSelected = dateOption == selected,
                modifier = Modifier.clickable {
                    onSelectedChange(dateOption)
                }
            )
        }
    }
}

@Composable
fun DateOptionItem(
    dateOption: FilterOption,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Surface {
        Text(
            text = dateOption.label,
            modifier = modifier.padding(keyLine2),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight =
                if (isSelected) FontWeight.Bold
                else FontWeight.Normal
            )
        )
    }
}
