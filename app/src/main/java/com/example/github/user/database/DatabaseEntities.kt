package com.example.github.user.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.github.user.domain.User

// Create a Room @Entity called user
@Entity(tableName = "user")
data class DatabaseUser constructor (
    @PrimaryKey(autoGenerate = true)
    val entryId: Long = 0L,
    val login: String,
    val id: Int,
    val name: String?,
    val email: String?,
    val bio: String?,
    val avatar_url: String,
    val location: String?,
    val public_repos: Int,
    val followers: Int,
    val following: Int
)

// Create a Room @Entity called follower
@Entity(tableName = "follower")
data class DatabaseFollower constructor (
    @PrimaryKey
    val login: String,
    val avatar_url: String
)

// Create a Room @Entity called following
@Entity(tableName = "following")
data class DatabaseFollowing constructor (
    @PrimaryKey
    val login: String,
    val avatar_url: String
)

// Extension function which converts from database objects to domain objects
fun List<DatabaseUser>.asDomainModel(): List<User> {
    return map {
        User(
            login = it.login,
            id = it.id,
            name = it.name,
            email = it.email,
            bio = it.bio,
            avatar_url = it.avatar_url,
            location = it.location,
            public_repos = it.public_repos,
            followers = it.followers,
            following = it.following)
    }
}