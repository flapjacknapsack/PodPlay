

package com.raywenderlich.podplay.model

import java.util.*

//This defines the data for a single podcast episode
data class Episode (
    var guid: String = "",
    var title: String = "",
    var description: String = "",
    var mediaUrl: String = "",
    var mimeType: String = "",
    var releaseDate: Date = Date(),
    var duration: String = ""
)
