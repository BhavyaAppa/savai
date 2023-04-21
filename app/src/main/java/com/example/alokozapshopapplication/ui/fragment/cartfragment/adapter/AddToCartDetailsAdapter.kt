package com.example.alokozapshopapplication.ui.fragment.cartfragment.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.RecyclarViewAddToCartItemBinding
import com.example.alokozapshopapplication.ui.fragment.cartfragment.`interface`.CartFragmentInterface
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.addtocartitem.AddToCartItemResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.`interface`.HomeFragmentItemClickInterface


class AddToCartDetailsAdapter(
    private val context: Context,
    private val listener: CartFragmentInterface,
    private var addCartItemList: ArrayList<AddToCartItemResponse.Item>
) : RecyclerView.Adapter<AddToCartDetailsAdapter.AddToCartViewHolder>() {


    class AddToCartViewHolder(val binding: RecyclarViewAddToCartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToCartViewHolder {
        return AddToCartViewHolder(
            RecyclarViewAddToCartItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: AddToCartViewHolder, position: Int) {
        holder.binding.addToCartItemViewModel = addCartItemList[position]


        holder.binding.tvAddToCartWishList.setOnClickListener {
            addCartItemList[position].let { it2 ->
                listener.onAddWishList(position, it, it2)
            }
        }

        holder.binding.cvAddToCart.setOnClickListener {
            addCartItemList[position].let { it2 ->
                listener.onWishListProductDetails(position, it, it2)
            }
        }

        holder.binding.tvAddToCartDelete.setOnClickListener {
            addCartItemList[position].let { it2 ->
                listener.onDeleteWishList(position, it, it2)
            }
        }

        Glide.with(context)
            .load(addCartItemList[position].image)
            .placeholder(R.drawable.ic_alokozap_app_logo)
            .centerCrop()
            .error(R.drawable.ic_alokozap_app_logo)
            .into(holder.binding.ivAddToCart)


    }

    override fun getItemCount(): Int {
        return addCartItemList.size
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAddToCartItemData(list:ArrayList<AddToCartItemResponse.Item>) {
        this.addCartItemList = list
        this.notifyDataSetChanged()
    }

}
