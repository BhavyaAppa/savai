package com.example.alokozapshopapplication.ui.fragment.searchfragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alokozapshopapplication.databinding.RecyclarViewHomeSubCategoryItemBinding
import com.example.alokozapshopapplication.databinding.RecyclarViewPopularSuggestionBinding
import com.example.alokozapshopapplication.ui.fragment.homefragment.`interface`.HomeFragmentItemClickInterface

import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.searchfragment.SearchListItemInterface
import com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel.SearchDataRespoance

class ParentProductListAdapter(
    private val context: Context,
    private val listener: SearchListItemInterface,
    private var productListNameData: java.util.ArrayList<SearchDataRespoance.Indice>,
    private  var childListNameData: java.util.ArrayList<SearchDataRespoance.Indice.Item>

    ) : RecyclerView.Adapter<ParentProductListAdapter.ProductListNameViewHolder>(){

    private lateinit var binding:RecyclarViewPopularSuggestionBinding

    class ProductListNameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListNameViewHolder {
        binding =
            RecyclarViewPopularSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentProductListAdapter.ProductListNameViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductListNameViewHolder, position: Int) {

        binding.popularSuggestionResponse =productListNameData[position]

        val child = productListNameData[position].items?.let { ArrayList(it) }
        binding.rvProductList.adapter= child?.let {
            ChildProductListAdapter(context,listener,
                it
            )
        }

        if(productListNameData[position].title?.contains("Products in categories") == true){
            binding.tvProductPopular.visibility=View.GONE

        }

        if(productListNameData[position].title?.contains("Products") == true){
            binding.tvProductPopular.visibility=View.GONE

        }


        binding.rvProductList.layoutManager=
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.VERTICAL,false)

    }

    override fun getItemCount(): Int {
        Log.e("Tag", productListNameData.size.toString())
        return productListNameData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: java.util.ArrayList<SearchDataRespoance.Indice>) {
        this.productListNameData = list
        this.notifyDataSetChanged()
    }

}