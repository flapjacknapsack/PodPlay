

package com.raywenderlich.podplay.service

import java.util.*

//represents all of the data you’ll retrieve from an RSS feed
data class RssFeedResponse(
    var title: String = "",
    var description: String = "",
    var summary: String = "",
    var lastUpdated: Date = Date(),
    var episodes: MutableList<EpisodeResponse>? = null
) {

  data class EpisodeResponse(
      var title: String? = null,
      var link: String? = null,
      var description: String? = null,
      var guid: String? = null,
      var pubDate: String? = null,
      var duration: String? = null,
      var url: String? = null,
      var type: String? = null
  )
}
