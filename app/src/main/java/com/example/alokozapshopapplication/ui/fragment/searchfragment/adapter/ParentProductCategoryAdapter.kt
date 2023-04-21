package com.example.alokozapshopapplication.ui.fragment.searchfragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alokozapshopapplication.databinding.RecyclarViewPopularSuggestionBinding
import com.example.alokozapshopapplication.databinding.RecyclarViewProductCategoryBinding
import com.example.alokozapshopapplication.ui.fragment.searchfragment.SearchListItemInterface
import com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel.SearchDataRespoance

class ParentProductCategoryAdapter(
    private val context: Context,
    private val listener: SearchListItemInterface,
    private var productListCatData: java.util.ArrayList<SearchDataRespoance.Indice>,
    private  var childListCatData: java.util.ArrayList<SearchDataRespoance.Indice.Item>

) : RecyclerView.Adapter<ParentProductCategoryAdapter.ParentProductCategoryViewHolder>(){

    private lateinit var binding:RecyclarViewProductCategoryBinding

    class ParentProductCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentProductCategoryViewHolder {
        binding =
            RecyclarViewProductCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentProductCategoryAdapter.ParentProductCategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ParentProductCategoryViewHolder, position: Int) {

        binding.productInCatResponse =productListCatData[position]

        val child = productListCatData[position].items?.let { ArrayList(it) }
        binding.rvProductCatList.adapter= child?.let {
            ChildProductCategoryAdapter(context,listener,
                it
            )
        }

        if(productListCatData[position].title?.contains("Popular suggestions") == true){
            binding.tvProductCategory.visibility=View.GONE
        }

        if(productListCatData[position].title?.contains("Products") == true){
            binding.tvProductCategory.visibility=View.GONE

        }

        if(productListCatData[position].title?.contains("Products in categories") == true){
            binding.tvProductCategory.visibility=View.VISIBLE

        }


        binding.rvProductCatList.layoutManager=
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.VERTICAL,false)

    }

    override fun getItemCount(): Int {
        Log.e("Tag", productListCatData.size.toString())
        return productListCatData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: java.util.ArrayList<SearchDataRespoance.Indice>) {
        this.productListCatData = list
        this.notifyDataSetChanged()
    }

}