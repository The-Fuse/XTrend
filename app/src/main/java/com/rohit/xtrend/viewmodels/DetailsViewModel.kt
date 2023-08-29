package com.rohit.xtrend.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohit.xtrend.models.TweetListItem
import com.rohit.xtrend.repository.XTrendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Thread.State
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: XTrendRepository) :
    ViewModel() {
    val tweets: StateFlow<List<TweetListItem>>
        get() = repository.tweets
    
    init {
        viewModelScope.launch {
            repository.getTweets("motivation")
        }
    }
}