package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.entity.User
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val cRepository: UserRepository = UserRepository(application)
    val allUsers: Flow<List<User>> = cRepository.allUsers

    suspend fun getUserByEmail(email: String): User? {
        return cRepository.getUserByEmail(email)
    }

    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.insert(user)
    }

    fun updateUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.update(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.delete(user)
    }
}