package com.example.starwarsdk.ui.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsdk.R
import com.example.starwarsdk.databinding.StarwarsItemLayoutBinding
import com.example.starwarsdk.utils.extensions.capitalizeWords


class DashboardAdaptor( private val adaptorCallback: AdaptorCallback<SdkEvents>, private val context:Context) : RecyclerView.Adapter<GenericViewHolder<*>>(){

    data class SdkEvents(
        val eventPosition: Int,
        val eventIcon:Int,
        val evenText:String
    )

    private val listOfEvents =  listOf<SdkEvents>(
        SdkEvents(eventPosition = 0, eventIcon = R.drawable.character_ic, evenText = context.getString(R.string.character_text)),
        SdkEvents(eventPosition = 1, eventIcon = R.drawable.planet_ic, evenText = context.getString(R.string.planet_text)),
        SdkEvents(eventPosition = 2, eventIcon = R.drawable.movie_ic_1, evenText = context.getString(R.string.film_text))
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<*> {
        val binding = StarwarsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding, adaptorCallback)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<*>, position: Int) {
        val item = listOfEvents[position]
        when(holder){
            is EventViewHolder -> item.let { holder.bind(it) }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return listOfEvents.size
    }


    inner class EventViewHolder(
        view: StarwarsItemLayoutBinding,
        adaptorCallback: AdaptorCallback<SdkEvents>
    ) :
        GenericViewHolder<SdkEvents>(view.root)  {
        private val listener = adaptorCallback
        private var itemBinding: StarwarsItemLayoutBinding = view

        private val text : TextView = itemBinding.txtEventName
        private val icon : ImageView = itemBinding.ivEventIcon
        private val container : ViewGroup = itemBinding.container

        override fun bind(item: SdkEvents) {
            text.text = item.evenText.capitalizeWords()
            icon.setImageResource(item.eventIcon)

            container.setOnClickListener{
                listener.onClicked(item)
            }
        }
    }


}