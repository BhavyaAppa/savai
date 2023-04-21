package com.example.alokozapshopapplication.ui.fragment.dealsfragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.DealsCategoryRecyclarViewBinding
import com.example.alokozapshopapplication.databinding.RecylarViewSubCategoryItemBinding
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.`interface`.CategoryItemInterFace
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.`interface`.DealsFragmentInterface
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.model.deals.DealsResponse


import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.CategoryItemListResponse
import java.security.AccessController.getContext


class DealsCategoryAdapter(

    private val context: Context,
    private val listener: DealsFragmentInterface,
    private var dealsData: ArrayList<DealsResponse.Carousel.Product>
) :
    RecyclerView.Adapter<DealsCategoryAdapter.DealsCategoryViewHolder>() {
    private lateinit var binding:DealsCategoryRecyclarViewBinding



    class DealsCategoryViewHolder(val binding:DealsCategoryRecyclarViewBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):DealsCategoryViewHolder {

        return DealsCategoryViewHolder( DealsCategoryRecyclarViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun getItemCount(): Int {
        Log.e("Tag", dealsData.size.toString())
        return dealsData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: ArrayList<DealsResponse.Carousel.Product>) {
        this.dealsData = list
        this.notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: DealsCategoryViewHolder, position: Int) {
        holder.binding.dealsResponceModel = dealsData[position]

        holder.binding.root.setOnClickListener {
            dealsData[position].let { it1 ->
                listener.onProductDetail(position, it, it1)
            }
        }

        holder.binding.btnAddToCart.setOnClickListener {
            dealsData[position].let { it2 ->
                listener.onAddCard(position,it,it2)
                if (holder.binding.btnAddToCart.visibility == View.VISIBLE) {
                    holder.binding.btnAddToCart.visibility = View.GONE
                    holder.binding.btnAddMinus.visibility = View.VISIBLE

                    var count = dealsData[position].minAddToCartQty!!.toInt()

                    if (holder.binding.btnAddMinus.visibility == View.VISIBLE) {
                        holder.binding.btnPlus.setOnClickListener {
                            ++count
                            holder.binding.tvItemTotal.text = count.toString()
                            count= dealsData[position].minAddToCartQty?.toInt()!!
                            notifyDataSetChanged()
                        }


                        holder.binding.btnMinus.setOnClickListener {
                            if (count >= 0) {
                                count--
                                holder.binding.tvItemTotal.text = count.toString()
                                notifyDataSetChanged()
                            }
                            if (count == 0) {
                                holder.binding.btnAddMinus.visibility = View.GONE
                                holder.binding.btnAddToCart.visibility = View.VISIBLE
                                holder.binding.tvItemTotal.text = "1"
                                notifyDataSetChanged()
                            }

                        }
                    }
                }

            }
        }

        holder.binding.ivHeart.setOnClickListener {
            dealsData[holder.adapterPosition].let { it2 ->
                if(dealsData[holder.adapterPosition].isInWishlist == true){
                    if (holder.binding.ivHeart.isSelected){
                        listener.onRemoveWishList(position, it, it2)
                        holder.binding.ivHeart.isSelected=false
                        dealsData[holder.adapterPosition].isInWishlist = false
                        notifyDataSetChanged()
                    }else{
                        holder.binding.ivHeart.isSelected = true
                        dealsData[holder.adapterPosition].isInWishlist = true
                        notifyDataSetChanged()
                    }
                }else{
                    if (!holder.binding.ivHeart.isSelected){
                        listener.onAddWishList(position, it, it2)
                        holder.binding.ivHeart.isSelected = true
                        dealsData[holder.adapterPosition].isInWishlist = true
                        notifyDataSetChanged()
                    }else{
                        holder.binding.ivHeart.isSelected = false
                        dealsData[holder.adapterPosition].isInWishlist = false
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }



}

