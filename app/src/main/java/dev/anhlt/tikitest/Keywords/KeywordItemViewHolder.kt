package dev.anhlt.tikitest.Keywords

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import dev.anhlt.tikitest.R

class KeywordItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var label: TextView? = null

    init {
        label = itemView.findViewById(R.id.tv_keyword)
    }

    fun bindData(keyword: String) {
        label?.text = keyword
        itemView.background.setColorFilter(randomColor(), PorterDuff.Mode.SRC_IN)
    }

    private fun randomColor(): Int {
        val colors = listOf("#906C00", "#6C2455", "#006CAA", "#904800", "#902400", "#6C0000", "#006C55")
        return Color.parseColor(colors[(0..colors.lastIndex).random()])
    }
}