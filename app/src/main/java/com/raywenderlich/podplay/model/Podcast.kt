

package com.raywenderlich.podplay.model

import java.util.*

//This defines the data for a single podcast
data class Podcast(
    var feedUrl: String = "",
    var feedTitle: String = "",
    var feedDesc: String = "",
    var imageUrl: String = "",
    var lastUpdated: Date = Date(),
    var episodes: List<Episode> = listOf()
)
