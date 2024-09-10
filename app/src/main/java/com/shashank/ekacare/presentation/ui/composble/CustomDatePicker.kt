package com.shashank.ekacare.presentation.ui.composble

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    selectedDate: Calendar,
    onDateChange: (Calendar) -> Unit,
    modifier: Modifier = Modifier
) {
    var dateText by remember { mutableStateOf("") }
    val context = LocalContext.current
    val dateFormatter = remember { SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) }

    LaunchedEffect(selectedDate) {
        dateText = dateFormatter.format(selectedDate.time)
    }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val newDate = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            onDateChange(newDate)
            dateText = dateFormatter.format(newDate.time)
        },
        selectedDate.get(Calendar.YEAR),
        selectedDate.get(Calendar.MONTH),
        selectedDate.get(Calendar.DAY_OF_MONTH)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clickable { datePickerDialog.show() }
    ) {
        OutlinedTextField(
            value = dateText,
            onValueChange = {},
            label = { Text("Date of Birth") },
            readOnly = true, // Prevent manual editing
            singleLine = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledBorderColor = Color.LightGray,
                disabledTextColor = Color.Black,
                disabledLabelColor = Color.Gray,
                disabledPlaceholderColor = Color.Gray
            )
        )
    }
}
