package com.example.spendtracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendtracker.data.repositories.STUserRepo
import com.example.spendtracker.models.database.tables.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class STMainViewModel @Inject constructor(
    private val userRepo: STUserRepo,
) : ViewModel() {

    private val _userEntity = MutableStateFlow<UserEntity?>(null)
    val userEntity: StateFlow<UserEntity?> = _userEntity


    init {
        viewModelScope.launch {
            userRepo.getLatestUser().collect { latestUser ->
                _userEntity.value = latestUser
            }
        }
    }
}