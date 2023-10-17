package com.akn.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private val quoteText : TextView
        get() = findViewById(R.id.quoteText)

    private val quoteAuthor : TextView
        get() = findViewById(R.id.quoteAuthor)

    private val tvNext : TextView
        get() = findViewById(R.id.tv_next)

    private val tvPrev : TextView
        get() = findViewById(R.id.tv_previous)

    private val fab : FloatingActionButton
        get() = findViewById(R.id.fab)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)

        setQuote(mainViewModel.getQuote())

        tvNext.setOnClickListener {
            setQuote(mainViewModel.nextQuote())
        }

        tvPrev.setOnClickListener {
            setQuote(mainViewModel.prevQuote())
        }

        fab.setOnClickListener{
            shareQuote()
        }
    }

    fun setQuote(quote: Quote) {
        quoteText.setText(quote.text)
        quoteAuthor.setText(quote.author)
    }

    fun shareQuote() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().toString())
        startActivity(intent)
    }
}