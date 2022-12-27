package com.example.github.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.github.user.database.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber
import java.io.IOException
import kotlin.reflect.typeOf

@RunWith(AndroidJUnit4::class)
class SearchDatabaseTest {

    private lateinit var searchDao: UserDao
    private lateinit var database: SearchDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        database = Room.inMemoryDatabaseBuilder(context, SearchDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        searchDao = database.userDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    private val userName = "johndoe"

    private val testUser = DatabaseUser(
        1,
        userName,
        1234,
        "John Doe",
        "johndoe@gmail.com",
        "Kotlin and Java Dev",
        "user_url",
        "Tanzania",
        15,
        10,
        20
    )

    //val fetchedFollowers: List<DatabaseFollower> =
    // *fetchedFollowers.toTypedArray()
    //  [DatabaseFollower(login=joshuamabina, avatar_url=https://avatars.githubusercontent.com/u/3260441?v=4)

    private val testFollowerList = listOf(
        DatabaseFollower("follower1", "follower_url_1"),
        DatabaseFollower("follower2", "follower_url_2")
    ).toTypedArray()

    private val testFollowingList = listOf(
        DatabaseFollowing("following1", "following_url_1"),
        DatabaseFollowing("following2", "following_url_2")
    ).toTypedArray()


    @Test
    fun insertUser() = runBlocking {
        searchDao.insertUser(testUser)

        val user = searchDao.getUser()

        Assert.assertNotNull(user)
    }

    @Test
    fun insertFollowers() = runBlocking {
        searchDao.insertFollowers(*testFollowerList)

        var followers = searchDao.getFollowers()

        Assert.assertNotNull(followers)
    }


    @Test
    fun insertFollowing() = runBlocking {
        searchDao.insertFollowing(*testFollowingList)

        val following = searchDao.getFollowing()

        Assert.assertNotNull(following)
    }

}