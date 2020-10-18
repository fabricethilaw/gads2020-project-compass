package com.thilawfabrice.compass.domain.entities

data class TipForRemoteWork(
    val id: String, val content: String,
    val author: Author,
    val categoryLabels: ArrayList<String>
)

