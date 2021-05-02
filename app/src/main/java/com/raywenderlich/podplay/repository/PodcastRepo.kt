

package com.raywenderlich.podplay.repository

import com.raywenderlich.podplay.model.Episode
import com.raywenderlich.podplay.model.Podcast
import com.raywenderlich.podplay.service.FeedService
import com.raywenderlich.podplay.service.RssFeedResponse
import com.raywenderlich.podplay.util.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PodcastRepo(private var feedService: FeedService) {
  //If the feedResponse is null, you pass null to the callBack method. If feedResponse is
  //valid, then you convert it to a Podcast object and pass it to the callback method.
  fun getPodcast(feedUrl: String, callback: (Podcast?) -> Unit) {

    feedService.getFeed(feedUrl) { feedResponse ->
      var podcast: Podcast? = null
      if (feedResponse != null) {
        podcast = rssResponseToPodcast(feedUrl, "", feedResponse)
      }
      GlobalScope.launch(Dispatchers.Main) {
        callback(podcast)
      }
    }
  }

  private fun rssResponseToPodcast(feedUrl: String, imageUrl: String, rssResponse:
  RssFeedResponse): Podcast? {

    val items = rssResponse.episodes ?: return null
    val description = if (rssResponse.description == "") rssResponse.summary else rssResponse.description

    return Podcast(feedUrl, rssResponse.title, description, imageUrl,
        rssResponse.lastUpdated, episodes = rssItemsToEpisodes(items))
  }

  //uses the map method to convert a list of EpisodeResponse objects into a list of
  //Episode objects
  private fun rssItemsToEpisodes(episodeResponses: List<RssFeedResponse.EpisodeResponse>): List<Episode> {
    return episodeResponses.map {
      Episode(
          it.guid ?: "",
          it.title ?: "",
          it.description ?: "",
          it.url ?: "",
          it.type ?: "",
          DateUtils.xmlDateToDate(it.pubDate),
          it.duration ?: ""
      )
    }
  }

}
