package com.example.myapplication.data.repository

import android.app.Application
import com.example.myapplication.data.DAO.UserDAO
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.entity.User
import kotlinx.coroutines.flow.Flow

class UserRepository(application: Application) {
    private var userDao: UserDAO =
        AppDatabase.getDatabase(application).userDAO()
    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }

    suspend fun delete(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun update(user: User) {
        userDao.updateUser(user)
    }
}