package com.example.confession

import com.google.firebase.database.ServerValue

// class for posts
class Post {
    private lateinit var key: String
    private lateinit var title: String
    private lateinit var subject: String
    private lateinit var description: String
    private lateinit var userId: String


    constructor()

    constructor(title: String, subject: String, description: String, userId: String) {
        this.title = title
        this.subject = subject
        this.description = description
        this.userId = userId
    }

    fun getTitle(): String {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getSubject(): String {
        return subject
    }

    fun setSubject(subject: String) {
        this.subject = subject
    }

    fun getDescription(): String {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getKey(): String {
        return key
    }

    fun setKey(key: String) {
        this.key = key
    }
    fun getUserId(): String {
        return userId
    }
    fun setUserId(userId: String) {
        this.userId = userId
    }
}

