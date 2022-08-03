package com.adityawasnik.quotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    private val quoteText: TextView
      get() = findViewById(R.id.quoteText)

    private val quoteAthor: TextView
        get() = findViewById(R.id.quoteAuhor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(applicationContext)).get(MainViewModel::class.java)

        setQuote(mainViewModel.getQuote())
    }

    private fun setQuote(quote: Quote) {
        quoteText.text = quote.text
        quoteAthor.text = quote.author
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote())
    }

    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }

    fun onShare(view: View) {
       val intent = Intent(Intent.ACTION_SEND)
       intent.setType("text/plain")
       intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
    }


}