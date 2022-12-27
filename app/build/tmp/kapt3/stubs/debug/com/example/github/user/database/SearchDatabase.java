package com.example.github.user.database;

import java.lang.System;

@androidx.room.Database(entities = {com.example.github.user.database.DatabaseUser.class, com.example.github.user.database.DatabaseFollower.class, com.example.github.user.database.DatabaseFollowing.class}, version = 1)
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/github/user/database/SearchDatabase;", "Landroidx/room/RoomDatabase;", "()V", "userDao", "Lcom/example/github/user/database/UserDao;", "getUserDao", "()Lcom/example/github/user/database/UserDao;", "app_debug"})
public abstract class SearchDatabase extends androidx.room.RoomDatabase {
    
    public SearchDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.github.user.database.UserDao getUserDao();
}