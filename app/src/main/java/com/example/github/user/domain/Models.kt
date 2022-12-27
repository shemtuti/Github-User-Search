package com.example.github.user.domain

// User represent a user that will be displayed
data class User(val login: String,
                val id: Int,
                val name: String?,
                val email: String?,
                val bio: String?,
                val avatar_url: String,
                val location: String?,
                val public_repos: Int,
                val followers: Int,
                val following: Int)

