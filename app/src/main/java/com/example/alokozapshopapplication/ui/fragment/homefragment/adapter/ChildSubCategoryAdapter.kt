package com.example.alokozapshopapplication.ui.fragment.homefragment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.ChildRecyclarViewSubCategoryBinding
import com.example.alokozapshopapplication.ui.fragment.homefragment.`interface`.HomeFragmentItemClickInterface
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray


class ChildSubCategoryAdapter(
    private val context: Context,
    private val listener: HomeFragmentItemClickInterface,
    private var subCategoryData: ArrayList<HomeResponse.Carousel.Product?>
) : RecyclerView.Adapter<ChildSubCategoryAdapter.CategoryViewHolder>() {


    class CategoryViewHolder(val binding: ChildRecyclarViewSubCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ChildRecyclarViewSubCategoryBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.homeSubCategoryViewModel = subCategoryData[position]
        holder.binding.ivHeart.isSelected = subCategoryData[position]?.isInWishlist == true
/*
        if (subCategoryData[position]?.isInWishlist == true){
            holder.binding.ivHeart.isSelected = true
        } else holder.binding.ivHeart.isSelected = false
*/

        holder.binding.root.setOnClickListener {
            subCategoryData[position]?.let { it1 ->
                listener.onSubItemClick(position, it, it1)
            }
        }


/*        holder.binding.btnAddToCart.setOnClickListener {
            holder.binding.isClick = true
            subCategoryData[position]?.let { it2 ->
                listener.onAddToCartClick(position, it, it2)
            }
        }

        holder.binding.btnMinus.setOnClickListener {
            subCategoryData[position]?.let { it2 ->
                listener.onRemoveCard(position, it, it2, count = 1)
            }
        }

        holder.binding.btnMinus.setOnClickListener {
            subCategoryData[position]?.let { it2 ->
                listener.onAddCard(position, it, it2, count = 1)
            }
        }*/

        holder.binding.ivHeart.setOnClickListener {
            subCategoryData[holder.adapterPosition]?.let { it2 ->
                if(subCategoryData[holder.adapterPosition]?.isInWishlist == true){
                    if (holder.binding.ivHeart.isSelected){
                        listener.onRemoveWishList(position, it, it2)
                        holder.binding.ivHeart.isSelected=false
                        subCategoryData[holder.adapterPosition]?.isInWishlist = false
                        notifyDataSetChanged()
                    }else{
                        holder.binding.ivHeart.isSelected = true
                        subCategoryData[holder.adapterPosition]?.isInWishlist = true
                        notifyDataSetChanged()
                    }
                }else{
                    if (!holder.binding.ivHeart.isSelected){
                        listener.onAddWishList(position, it, it2)
                        holder.binding.ivHeart.isSelected = true
                        subCategoryData[holder.adapterPosition]?.isInWishlist = true
                        notifyDataSetChanged()
                    }else{
                        holder.binding.ivHeart.isSelected = false
                        subCategoryData[holder.adapterPosition]?.isInWishlist = false
                        notifyDataSetChanged()
                    }
                }


            }
        }


        holder.binding.btnAddToCart.setOnClickListener {
            subCategoryData[position]?.let { it2 ->
                listener.onAddToCartClick(position,it,it2)
                if (holder.binding.btnAddToCart.visibility == View.VISIBLE) {
                    holder.binding.btnAddToCart.visibility = View.GONE
                    holder.binding.btnAddMinus.visibility = View.VISIBLE

                    var count = subCategoryData[position]?.minAddToCartQty!!.toInt()

                    if (holder.binding.btnAddMinus.visibility == View.VISIBLE) {
                        holder.binding.btnPlus.setOnClickListener {
                            ++count
                            holder.binding.tvItemTotal.text = count.toString()

                            count= subCategoryData[position]?.minAddToCartQty?.toInt()!!
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

        Glide.with(context)
            .load(subCategoryData[position]?.thumbNail)
            .centerCrop()
            .thumbnail(Glide.with(context).load("https://raw.githubusercontent.com/ybq/Android-SpinKit/master/art/Pulse.gif"))
            .into(holder.binding.ivSubCategory)

    }

    override fun getItemCount(): Int {
        Log.e("Tag", subCategoryData.size.toString())
        return subCategoryData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: ArrayList<HomeResponse.Carousel.Product?>) {
        this.subCategoryData = list
        this.notifyDataSetChanged()
    }


}