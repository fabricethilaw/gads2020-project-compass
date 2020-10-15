package com.thilawfabrice.compass.domain

data class TipForRemoteWork(
    val id: String, val content: String,
    val author: Author,
    val categoryLabels: ArrayList<String>
)

data class Author(val name: String, val picture: String)