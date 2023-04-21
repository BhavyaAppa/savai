package com.example.alokozapshopapplication.ui.fragment.mywishlist.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alokozapshopapplication.databinding.WishListRecyclarViewBinding

import com.example.alokozapshopapplication.ui.fragment.mywishlist.`interface`.WishListInterface
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.getwishlist.WishListResponse


class WishListAdapter(

    private val context: Context,
    private val listener: WishListInterface,
    private var wishListData: ArrayList<WishListResponse.Wish>
) :
    RecyclerView.Adapter<WishListAdapter.WishListViewHolder>() {

    class WishListViewHolder(val binding: WishListRecyclarViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {

        return WishListViewHolder(
            WishListRecyclarViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        Log.e("Tag", wishListData.size.toString())
        return wishListData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: ArrayList<WishListResponse.Wish>) {
        this.wishListData = list
        this.notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        holder.binding.wishListResponce = wishListData[position]


        holder.binding.root.setOnClickListener {
            wishListData[position].let { it2 ->
                listener.onProductDetails(position, it, it2)
            }
        }

        holder.binding.btnAddToCart.setOnClickListener {
            holder.binding.isClick = true
            wishListData[position].let { it2 ->
                listener.onAddCard(position, it, it2)
            }
        }
        holder.binding.ivHeart.setOnClickListener {
            holder.binding.isClick = true
            wishListData[position].let { it2 ->
                listener.onItemDelete(position, it, it2)
            }
        }
    }
}

