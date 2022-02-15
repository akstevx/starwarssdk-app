package com.example.starwarsdk.ui.adaptor

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class GenericViewHolder <in T> (itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind (item: T)
}

interface AdaptorCallback<T> {
    fun onClicked( item: T)
}