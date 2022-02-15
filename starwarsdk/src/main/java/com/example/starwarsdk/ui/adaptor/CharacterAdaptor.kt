package com.example.starwarsdk.ui.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsdk.R
import com.example.starwarsdk.databinding.ItemLayoutBinding
import com.example.starwarsdk.network.response.GetPersonResponse
import com.example.starwarsdk.utils.extensions.capitalizeWords
import com.example.starwarsdk.utils.extensions.hide

class CharacterAdaptor(private val adaptorCallback: AdaptorCallback<GetPersonResponse>,
                       private val listOfCharacters: MutableList<GetPersonResponse>) : RecyclerView.Adapter<GenericViewHolder<*>>(){
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<*> {
        context = parent.context
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, adaptorCallback)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<*>, position: Int) {
        val item = listOfCharacters[position]
        when(holder){
            is CharacterViewHolder -> item.let { holder.bind(it) }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return listOfCharacters.size
    }

    fun onNewData(newData: List<GetPersonResponse>){
        listOfCharacters.clear()
        listOfCharacters.addAll(newData)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(
        view: ItemLayoutBinding,
        adaptorCallback: AdaptorCallback<GetPersonResponse>
    ) :
        GenericViewHolder<GetPersonResponse>(view.root)  {
        private val listener = adaptorCallback
        private var itemBinding: ItemLayoutBinding = view

        private val nameText : TextView = itemBinding.txtName
        private val header : TextView = itemBinding.txtHeader1
        private val eyeText : TextView = itemBinding.txtValue1
        private val header2 : TextView = itemBinding.txtHeader2
        private val subText : TextView = itemBinding.txtValue2
        private val createdText : TextView = itemBinding.txtValue3
        private val icon : ImageView = itemBinding.ivIcon
        private val container : ViewGroup = itemBinding.container

        override fun bind(item: GetPersonResponse) {
            header2.hide()
            subText.hide()
            nameText.text = "${item.name?.capitalizeWords()}"
            header.text = context.getString(R.string.eye_color)
            eyeText.text = "${item.eye_color}"
            createdText.text = item.created

            when(item.gender){
                "male" -> icon.setImageResource(R.drawable.male_character_ic)
                "female" -> icon.setImageResource(R.drawable.female_character_ic)
                else -> icon.setImageResource(R.drawable.character_ic)
            }

            container.setOnClickListener{
                listener.onClicked(item)
            }
        }
    }

}