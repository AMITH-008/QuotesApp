package com.akn.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context : Context) : ViewModel() {

    private  var quoteList : Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuotesFromAsset()
    }

    fun getQuote(): Quote {
        return quoteList.get(index)
    }

    fun nextQuote() : Quote {
        if(index == quoteList.size-1){
            index = 0
        }else{
            index+=1
        }
        return getQuote()
    }

    fun prevQuote(): Quote {
        if(index == 0){
            index = quoteList.size-1
        }else{
            index -= 1
        }
        return getQuote()
    }

    private fun loadQuotesFromAsset(): Array<Quote> {
        val inputStream = context.assets.open("Quotes.json")
        val size:Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

}