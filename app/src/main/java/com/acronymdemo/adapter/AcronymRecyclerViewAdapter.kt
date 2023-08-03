package com.acronymdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.acronymdemo.R
import com.acronymdemo.databinding.AcronymListItemBinding
import com.acronymdemo.model.AcronymDataModel

class AcronymRecyclerViewAdapter(private val list: List<AcronymDataModel>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: AcronymListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.acronym_list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(list[position])
    }
}

class MyViewHolder(private val listItemBinding: AcronymListItemBinding) :
    RecyclerView.ViewHolder(listItemBinding.root) {
    fun bind(acronymDataModel: AcronymDataModel) {
        listItemBinding.acronymWord.text = acronymDataModel.word
        listItemBinding.acronymDefinition.text = acronymDataModel.meanings[0].definition[0].definition
        //listItemBinding.acronymSynonyms.text = acronymDataModel.meanings[0].synonyms[0]
        //listItemBinding.acronymAntonyms.text = acronymDataModel.meanings[0].antonyms[0]
    }
}