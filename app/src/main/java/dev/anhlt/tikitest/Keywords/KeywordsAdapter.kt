package dev.anhlt.tikitest.Keywords

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import dev.anhlt.tikitest.R

class KeywordsAdapter(var keywords: List<String>) : RecyclerView.Adapter<KeywordItemViewHolder>() {
    fun reloadData(data: List<String>) {
        keywords = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(itemview: ViewGroup, index: Int): KeywordItemViewHolder {
        val itemView = View.inflate(itemview.context, R.layout.layout_keyword_item, null)
        return KeywordItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return keywords.size
    }

    override fun onBindViewHolder(holder: KeywordItemViewHolder, index: Int) {
        holder.bindData(keywords[index])
    }
}