package com.example.github.user.network;

import java.lang.System;

/**
 * A retrofit service to fetch a user
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'J\u001e\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'J\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\f\u00c0\u0006\u0001"}, d2 = {"Lcom/example/github/user/network/GithubUserService;", "", "fetchFollowers", "Lkotlinx/coroutines/Deferred;", "", "Lcom/example/github/user/database/DatabaseFollower;", "user", "", "fetchFollowing", "Lcom/example/github/user/database/DatabaseFollowing;", "fetchUser", "Lcom/example/github/user/database/DatabaseUser;", "app_debug"})
public abstract interface GithubUserService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "users/{user}")
    public abstract kotlinx.coroutines.Deferred<com.example.github.user.database.DatabaseUser> fetchUser(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "user")
    java.lang.String user);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "users/{user}/followers")
    public abstract kotlinx.coroutines.Deferred<java.util.List<com.example.github.user.database.DatabaseFollower>> fetchFollowers(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "user")
    java.lang.String user);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "users/{user}/following")
    public abstract kotlinx.coroutines.Deferred<java.util.List<com.example.github.user.database.DatabaseFollowing>> fetchFollowing(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "user")
    java.lang.String user);
}