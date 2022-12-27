package com.example.github.user.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    // insert single user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: DatabaseUser)

    // Return the latest user
    @Query("SELECT * FROM user ORDER BY entryId DESC LIMIT 1")
    fun getUser(): LiveData<List<DatabaseUser>>

    // insert all followers
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFollowers(vararg follower: DatabaseFollower)

    // get all followers
    @Query("SELECT * FROM follower")
    fun getFollowers(): LiveData<List<DatabaseFollower>>

    // insert all following
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFollowing(vararg following: DatabaseFollowing)

    // get all following
    @Query("SELECT * FROM following")
    fun getFollowing(): LiveData<List<DatabaseFollowing>>

    @Query("DELETE FROM follower")
    fun deleteFollower()

    @Query("DELETE FROM following")
    fun deleteFollowing()

}





