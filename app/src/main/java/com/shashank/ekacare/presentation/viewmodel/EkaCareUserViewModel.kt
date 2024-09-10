package com.shashank.ekacare.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.ekacare.data.local.entity.EkaCareUser
import com.shashank.ekacare.domain.repository.EkaCareUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EkaCareUserViewModel @Inject constructor(private val repository: EkaCareUserRepository) :
    ViewModel() {

    // StateFlow to observe user list
    private val _ekaCareUserList = MutableStateFlow<List<EkaCareUser>>(emptyList())
    val ekaCareUserList: StateFlow<List<EkaCareUser>> = _ekaCareUserList

    init {
        // Collecting user list when ViewModel is created
        viewModelScope.launch {
            repository.getEkaCareUserList().collect { users ->
                _ekaCareUserList.value = users
            }
        }
    }

    // Method to add a user
    fun addUser(ekaCareUser: EkaCareUser) {
        viewModelScope.launch {
            repository.addUser(ekaCareUser)
        }
    }
}