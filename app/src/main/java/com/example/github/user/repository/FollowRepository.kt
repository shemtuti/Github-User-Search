package com.example.github.user.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.github.user.database.DatabaseFollower
import com.example.github.user.database.DatabaseFollowing
import com.example.github.user.database.SearchDatabase
import com.example.github.user.database.asDomainModel
import com.example.github.user.domain.User
import com.example.github.user.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Repository for fetching data from the network and storing them on disk
 */
class FollowRepository(private val database: SearchDatabase) {
    private var userName = ""

    // Fetch user data from the network and put it in the DB
    suspend fun fetchUserData(userName: String) {
        this.userName = userName
        withContext(Dispatchers.IO) {
            try{
                val fetchedData = Network.githubService.fetchUser(userName).await()
                Timber.i("DATA-U-Repo: $fetchedData")
                database.userDao.insertUser(fetchedData)

            }
            catch (e:Exception){
                Timber.i("DATA: ${e.message}")
            }
        }
    }

    // Fetch followers data from the network and put it in the DB
    suspend fun fetchUserFollowers(userName: String) {
        withContext(Dispatchers.IO) {
            try{
                val fetchedFollowers = Network.githubService.fetchFollowers(userName).await()
                database.userDao.deleteFollower()
                Timber.i("DATA-F-Repo: $fetchedFollowers")
                database.userDao.insertFollowers(*fetchedFollowers.toTypedArray())
            }
            catch (e:Exception){
                Timber.i("DATA-F: ${e.message}")
            }
        }
    }

    // Fetch following data from the network and put it in the DB
    suspend fun fetchUserFollowing(userName: String) {
        withContext(Dispatchers.IO) {
            try{
                val fetchedFollowing = Network.githubService.fetchFollowing(userName).await()
                database.userDao.deleteFollowing()
                Timber.i("DATA-Fng-Repo: $fetchedFollowing")
                database.userDao.insertFollowing(*fetchedFollowing.toTypedArray())
            }
            catch (e:Exception){
                Timber.i("DATA-Fng: ${e.message}")
            }
        }
    }

    // map output of one LiveData to another type (user data)
    val userData: LiveData<List<User>> = Transformations.map(database.userDao.getUser()){
        it.asDomainModel()
    }

    // get followers from the DAO class
    val userFollowers: LiveData<List<DatabaseFollower>> = database.userDao.getFollowers()

    // get following from the DAO class
    val userFollowing: LiveData<List<DatabaseFollowing>> = database.userDao.getFollowing()

}


















