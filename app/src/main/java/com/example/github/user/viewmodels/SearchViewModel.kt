package com.example.github.user.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.github.user.database.getDatabase
import com.example.github.user.repository.FollowRepository
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val searchRepository = FollowRepository(database)

    private var userName = ""

    // fetch user details from server
    fun fetchUserDetails(userName: String) {
        this.userName = userName
        viewModelScope.launch {
            searchRepository.fetchUserData(userName)

            fetchUserFollowers(userName)
            fetchUserFollowing(userName)
        }
    }

    // fetch followers details from server
    fun fetchUserFollowers(userName: String) {
        this.userName = userName
        viewModelScope.launch {
            searchRepository.fetchUserFollowers(userName)
        }
    }

    // fetch following details from server
    private fun fetchUserFollowing(userName: String) {
        this.userName = userName
        viewModelScope.launch {
            searchRepository.fetchUserFollowing(userName)
        }
    }

    // get github user details from room
    val userInfo = searchRepository.userData

    // get github followers details from room
    val userFollowers = searchRepository.userFollowers

    // get github following details from room
    val userFollowing = searchRepository.userFollowing

    // Factory for constructing SearchViewModel with parameter
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
