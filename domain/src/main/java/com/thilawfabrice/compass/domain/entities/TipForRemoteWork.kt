package com.thilawfabrice.compass.domain.entities

class TipForRemoteWork {
    var id: String = ""
    var content: String = ""
    var author: Author? = null
    var topics: ArrayList<String> = arrayListOf()

    constructor()

    constructor(
        id: String,
        content: String,
        author: Author,
        topics: ArrayList<String>
    ) {
        this.id = id
        this.content = content
        this.author = author
        this.topics = topics
    }
}