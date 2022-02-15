package com.example.starwarsdk.ui.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsdk.R
import com.example.starwarsdk.databinding.ItemLayoutBinding
import com.example.starwarsdk.network.response.GetFilmResponse
import com.example.starwarsdk.utils.extensions.capitalizeWords
import com.example.starwarsdk.utils.extensions.hide


class FilmAdaptor(private val adaptorCallback: AdaptorCallback<GetFilmResponse>,
                    private val listOfFilms: MutableList<GetFilmResponse>) : RecyclerView.Adapter<GenericViewHolder<*>>(){
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<*> {
        context = parent.context
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding,adaptorCallback)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<*>, position: Int) {
        val item = listOfFilms[position]
        when(holder){
            is FilmViewHolder -> item.let { holder.bind(it) }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return listOfFilms.size
    }

    fun onNewData(newData: List<GetFilmResponse>){
        listOfFilms.clear()
        listOfFilms.addAll(newData)
        notifyDataSetChanged()
    }

    inner class FilmViewHolder(
        view: ItemLayoutBinding,
        adaptorCallback: AdaptorCallback<GetFilmResponse>
    ) :
        GenericViewHolder<GetFilmResponse>(view.root) {
        private val listener = adaptorCallback
        private var itemBinding: ItemLayoutBinding = view

        private val nameText: TextView = itemBinding.txtName
        private val header: TextView = itemBinding.txtHeader1
        private val narrationText: TextView = itemBinding.txtValue1
        private val header2: TextView = itemBinding.txtHeader2
        private val subText: TextView = itemBinding.txtValue2
        private val header3: TextView = itemBinding.txtHeader3
        private val createdText: TextView = itemBinding.txtValue3
        private val icon: ImageView = itemBinding.ivIcon
        private val container: ViewGroup = itemBinding.container

        override fun bind(item: GetFilmResponse) {
            header2.hide()
            subText.hide()
            nameText.text = "${item.title?.capitalizeWords()}"
            icon.setImageResource(R.drawable.movie_ic_1)

            header.text = context.getString(R.string.opening_crawl)
            narrationText.text = "${item.opening_crawl}"

            header3.text = context.getString(R.string.release_year)
            createdText.text = item.created

            container.setOnClickListener {
                listener.onClicked(item)
            }
        }
    }

}