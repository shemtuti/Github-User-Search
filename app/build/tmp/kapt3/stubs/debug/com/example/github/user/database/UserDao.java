package com.example.github.user.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\b\u0010\u0004\u001a\u00020\u0003H\'J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\'J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006H\'J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\u0006H\'J!\u0010\r\u001a\u00020\u00032\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u000f\"\u00020\bH\'\u00a2\u0006\u0002\u0010\u0010J!\u0010\u0011\u001a\u00020\u00032\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u000f\"\u00020\nH\'\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\fH\'\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u0016\u00c0\u0006\u0001"}, d2 = {"Lcom/example/github/user/database/UserDao;", "", "deleteFollower", "", "deleteFollowing", "getFollowers", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/github/user/database/DatabaseFollower;", "getFollowing", "Lcom/example/github/user/database/DatabaseFollowing;", "getUser", "Lcom/example/github/user/database/DatabaseUser;", "insertFollowers", "follower", "", "([Lcom/example/github/user/database/DatabaseFollower;)V", "insertFollowing", "following", "([Lcom/example/github/user/database/DatabaseFollowing;)V", "insertUser", "users", "app_debug"})
public abstract interface UserDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertUser(@org.jetbrains.annotations.NotNull()
    com.example.github.user.database.DatabaseUser users);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM user ORDER BY entryId DESC LIMIT 1")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.github.user.database.DatabaseUser>> getUser();
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertFollowers(@org.jetbrains.annotations.NotNull()
    com.example.github.user.database.DatabaseFollower... follower);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM follower")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.github.user.database.DatabaseFollower>> getFollowers();
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertFollowing(@org.jetbrains.annotations.NotNull()
    com.example.github.user.database.DatabaseFollowing... following);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM following")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.github.user.database.DatabaseFollowing>> getFollowing();
    
    @androidx.room.Query(value = "DELETE FROM follower")
    public abstract void deleteFollower();
    
    @androidx.room.Query(value = "DELETE FROM following")
    public abstract void deleteFollowing();
}