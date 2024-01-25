package com.example.confession

// class for comments
class Comment {
    private lateinit var content : String

    constructor()


    constructor(content: String) {
        this.content = content
    }

    fun getContent(): String {
        return content
    }

    fun setContent(content: String) {
        this.content = content
    }

}