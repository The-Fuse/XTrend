package com.rohit.xtrend.repository

import com.rohit.xtrend.api.XTrendApi
import com.rohit.xtrend.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import javax.inject.Inject

class XTrendRepository @Inject constructor(private val xTrendApi: XTrendApi) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets

    suspend fun getCategories(){
        val response =  xTrendApi.getCategories()
        if (response.isSuccessful && response.body() != null){
            _categories.emit(response.body()!!)
        }
    }
    suspend fun getTweets(category: String){
        val response =  xTrendApi.getTweets("tweets[?(@.category==\"$category\")]")
        if (response.isSuccessful && response.body() != null){
            _tweets.emit(response.body()!!)
        }
    }
}