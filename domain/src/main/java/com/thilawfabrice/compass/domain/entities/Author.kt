package com.thilawfabrice.compass.domain.entities

class Author {
    var name: String = ""
    var picture: String? = null
    var role: String = ""

    constructor()

    constructor(name: String, picture: String?, role: String) {
        this.name = name
        this.picture = picture
        this.role = role
    }
}