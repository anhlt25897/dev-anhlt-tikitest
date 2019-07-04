package dev.anhlt.tikitest

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.util.TypedValue
import dev.anhlt.tikitest.APIs.FeedDataManager
import dev.anhlt.tikitest.Keywords.HSpaceDecoration
import dev.anhlt.tikitest.Keywords.KeywordsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        loadData()
    }

    private fun setupViews() {
        rv_keywords.adapter = KeywordsAdapter(listOf())
        rv_keywords.itemAnimator = DefaultItemAnimator()
        rv_keywords.addItemDecoration(HSpaceDecoration(dipToPixels(4f).toInt()))
        rv_keywords.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun loadData() {
        FeedDataManager.getKeywords(success = { data ->
            //reload with new data
            (rv_keywords.adapter as? KeywordsAdapter)?.reloadData(data.map {
                reformatKeyword(it)
            })
        }, failed = { error ->
            //log error
            Log.d("FeedActivity", error.message)
        })
    }

    private fun reformatKeyword(input: String): String {
        val words = input.split(' ').dropLastWhile { it.isEmpty() }.toTypedArray()
        //special cases
        //only one word
        if (words.size <= 1) {
            return input
        }
        //2 words
        if (words.size == 2) {
            return words[0].trim { it <= ' ' } + "\n" + words[1].trim { it <= ' ' }
        }
        //space in center
        val centerIndex = input.length / 2
        if (input[centerIndex] == ' ') {
            return input.substring(0, centerIndex) + "\n" + input.substring(centerIndex + 1)
        }

        //other cases
        //center cross over a word
        var currSpace = -1
        var nextSpace: Int
        for (word in words) {
            nextSpace = currSpace + word.length + 1
            //this's word crossed
            if (centerIndex in (currSpace + 1) until nextSpace) {
                //check to put word to 1st line or 2nd line
                //1st line
                if (currSpace <= input.length - (nextSpace + 1)) {
                    currSpace = nextSpace
                }
                //2nd line
                break
            }
            //count space
            currSpace = nextSpace
        }
        return input.substring(0, currSpace) + "\n" + input.substring(currSpace + 1)
    }
}

//extension
fun Context.dipToPixels(dipValue: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, resources.displayMetrics)