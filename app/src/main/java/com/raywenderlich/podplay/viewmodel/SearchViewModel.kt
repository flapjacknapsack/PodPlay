

package com.raywenderlich.podplay.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.raywenderlich.podplay.repository.ItunesRepo
import com.raywenderlich.podplay.service.PodcastResponse
import com.raywenderlich.podplay.util.DateUtils

class SearchViewModel(application: Application) : AndroidViewModel(application) {

  //Property for an ItunesRepo which will fetch the information
  var iTunesRepo: ItunesRepo? = null

  data class PodcastSummaryViewData(
      var name: String? = "",
      var lastUpdated: String? = "",
      var imageUrl: String? = "",
      var feedUrl: String? = "")

  //Converts raw info to viewable data
  private fun itunesPodcastToPodcastSummaryView(
      itunesPodcast: PodcastResponse.ItunesPodcast):
      PodcastSummaryViewData {
    return PodcastSummaryViewData(
        itunesPodcast.collectionCensoredName,
        DateUtils.jsonDateToShortDate(itunesPodcast.releaseDate),
        itunesPodcast.artworkUrl30,
        itunesPodcast.feedUrl)
  }

  fun searchPodcasts(term: String, callback: (List<PodcastSummaryViewData>) -> Unit) {
    iTunesRepo?.searchByTerm(term) { results ->
      if (results == null) {
        callback(emptyList())
      } else {
        val searchViews = results.map { podcast ->
          itunesPodcastToPodcastSummaryView(podcast)
        }
        callback(searchViews)
      }
    }
  }
}
