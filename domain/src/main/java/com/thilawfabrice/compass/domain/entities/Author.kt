package com.thilawfabrice.compass.domain.entities

/**
 * The author of a best pratice for working remotely
 */
class Author {
    var name: String = ""
    var picture: String? = null
    var role: String = ""
    var companyName: String = ""
    var companyUrl: String = ""

    constructor()

    constructor(
        name: String,
        picture: String?,
        role: String,
        companyName: String,
        companyUrl: String
    ) {
        this.name = name
        this.picture = picture
        this.role = role
        this.companyName = companyName
        this.companyUrl = companyUrl
    }
}