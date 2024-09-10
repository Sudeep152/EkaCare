package com.shashank.ekacare.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shashank.ekacare.data.local.entity.EkaCareUser
import com.shashank.ekacare.presentation.ui.composble.CustomDatePicker
import com.shashank.ekacare.presentation.ui.composble.CustomTextField
import com.shashank.ekacare.presentation.ui.composble.SingleEkaCareUser
import com.shashank.ekacare.presentation.viewmodel.EkaCareUserViewModel
import java.util.Calendar

@Composable
fun EkaCareHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: EkaCareUserViewModel = hiltViewModel()
) {
    val userList by viewModel.ekaCareUserList.collectAsState()
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf(Calendar.getInstance()) }
    var address by remember { mutableStateOf("") }
    var isFormValid by remember { mutableStateOf(true) }

    fun validateForm(): Boolean {
        val isNameValid = name.isNotBlank()
        val isAgeValid = age.toIntOrNull() != null
        val isAddressValid = address.isNotBlank()

        isFormValid = isNameValid && isAgeValid && isAddressValid
        return isFormValid
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            value = name,
            onValueChange = { name = it },
            label = "Name",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            onImeAction = { /* Handle Next Action */ }
        )

        CustomTextField(
            value = age,
            onValueChange = { age = it },
            label = "Age",
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next,
            onImeAction = { /* Handle Next Action */ }
        )

        // Corrected DatePicker
        CustomDatePicker(
            selectedDate = dob,
            onDateChange = { dob = it },
            modifier = Modifier.fillMaxWidth()
        )


        CustomTextField(
            value = address,
            onValueChange = { address = it },
            label = "Address",
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
            onImeAction = { /* Handle Done Action */ }
        )

        if (!isFormValid) {
            Text("Please fill in all fields correctly", color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (validateForm()) {
                    viewModel.addUser(
                        EkaCareUser(
                            name = name,
                            age = age.toInt(),
                            dob = dob.toString(),
                            address = address
                        )
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(15.dp)
        ) {
            Text("Submit")
        }
        LazyColumn() {
            items(userList) { user ->
                SingleEkaCareUser(user)
            }
        }
    }
}
