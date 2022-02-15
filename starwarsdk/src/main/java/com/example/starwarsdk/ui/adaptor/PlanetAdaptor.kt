package com.example.starwarsdk.ui.adaptor

import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsdk.R
import com.example.starwarsdk.databinding.ItemLayoutBinding
import com.example.starwarsdk.network.response.GetPlanetResponse
import com.example.starwarsdk.utils.extensions.capitalizeWords
import com.example.starwarsdk.utils.extensions.formatNumber


class PlanetAdaptor(private val adaptorCallback: AdaptorCallback<GetPlanetResponse>,
                       private val listOfPlanets: MutableList<GetPlanetResponse>) : RecyclerView.Adapter<GenericViewHolder<*>>(){
    private lateinit var context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<*> {
        context = parent.context
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanetViewHolder(binding,context ,adaptorCallback)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<*>, position: Int) {
        val item = listOfPlanets[position]
        when(holder){
            is PlanetViewHolder -> item.let { holder.bind(it) }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return listOfPlanets.size
    }


    fun onNewData(newData: List<GetPlanetResponse>){
        listOfPlanets.clear()
        listOfPlanets.addAll(newData)
        notifyDataSetChanged()
    }

    inner class PlanetViewHolder(
        view: ItemLayoutBinding,
        context:Context,
        adaptorCallback: AdaptorCallback<GetPlanetResponse>
    ) : GenericViewHolder<GetPlanetResponse>(view.root)  {
        private val listener = adaptorCallback
        private var itemBinding: ItemLayoutBinding = view
        private val mContext = context

        private val planetName : TextView = itemBinding.txtName
        private val header : TextView = itemBinding.txtHeader1
        private val planetPopulation : TextView = itemBinding.txtValue1
        private val header2 : TextView = itemBinding.txtHeader2
        private val planetClimate: TextView = itemBinding.txtValue2
        private val createdAt: TextView = itemBinding.txtValue3
        private val planetIcon : ImageView = itemBinding.ivIcon
        private val container : ViewGroup = itemBinding.container

        override fun bind(item: GetPlanetResponse) {
            planetName.text = item.name?.capitalizeWords()
            header.text = mContext.getString(R.string.population)
            planetPopulation.text = item.population?.formatNumber()
            header2.text = mContext.getString(R.string.climate)
            planetClimate.text = item.climate
            createdAt.text = item.created
            planetIcon.setImageResource(getRandomPlanetResource())

            container.setOnClickListener{
                listener.onClicked(item)
            }
        }

        private fun getRandomPlanetResource(): Int{
            val images: TypedArray = mContext.resources.obtainTypedArray(R.array.loading_planets)
            val choice = (Math.random() * images.length()).toInt()
            return images.getResourceId(choice, R.drawable.planet_ic)
        }

    }

}