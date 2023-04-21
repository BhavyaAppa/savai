package com.example.alokozapshopapplication.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.RecylarViewSubCategoryItemBinding
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.`interface`.CategoryItemInterFace


import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.CategoryItemListResponse
import java.security.AccessController.getContext


class CategorySubAdapter(

    private val context: Context,
    private val listener: CategoryItemInterFace,
    private var categoryData: ArrayList<CategoryItemListResponse.Product>
) :
    RecyclerView.Adapter<CategorySubAdapter.CategoryItemViewHolder>() {
    private lateinit var binding:RecylarViewSubCategoryItemBinding



    class CategoryItemViewHolder(val binding:RecylarViewSubCategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CategoryItemViewHolder {

        return CategoryItemViewHolder( RecylarViewSubCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun getItemCount(): Int {
        Log.e("Tag", categoryData.size.toString())
        return categoryData.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCategoryItemData(list: ArrayList<CategoryItemListResponse.Product>) {
        this.categoryData = list
        this.notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.binding.categoryItemListModel = categoryData[position]


        holder.binding.root.setOnClickListener {
            categoryData[position].let { it1 ->
                listener.onProductDetailClick(position, it, it1)
            }
        }



        holder.binding.btnAddToCart.setOnClickListener {

            holder.binding.isClick = true
            categoryData[position].let { it2 ->
                listener.onAddToCartClick(position, it, it2)
            }
        }

        holder.binding.btnMinus.setOnClickListener {
            categoryData[position].let { it2 ->
                listener.onRemoveCard(position, it, it2, count = 1)
            }
        }

        holder.binding.btnMinus.setOnClickListener {
            categoryData[position].let { it2 ->
                listener.onAddCard(position, it, it2, count = 1)
            }
        }

        holder.binding.ivHeart.setOnClickListener {
            categoryData[position].let { it2 ->
                listener.wishList(position, it, it2)
            }
        }


/*        holder.binding.btnAddToCart.setOnClickListener {
           if (holder.binding.btnAddMinus.visibility==View.GONE){

               holder.binding.btnAddMinus.visibility=View.VISIBLE
               holder.binding.btnAddToCart.visibility=View.GONE

               var count = categoryData[position].minAddToCartQty!!.toInt()

               if (holder.binding.btnAddMinus.visibility == View.VISIBLE) {
                   holder.binding.btnPlus.setOnClickListener {
                       ++count
                       holder.binding.tvItemTotal.text = count.toString()

                   }
                   holder.binding.btnMinus.setOnClickListener {

                       if (count >= 0) {
                           count--
                           holder.binding.tvItemTotal.text = count.toString()

                       }
                       if (count == 0) {
                           holder.binding.btnAddMinus.visibility = View.GONE
                           holder.binding.btnAddToCart.visibility = View.VISIBLE
                           holder.binding.tvItemTotal.text = "1"
                       }

                   }
               }

           }
        }*/


/*        holder.binding.btnAddToCart.setOnClickListener {
            categoryData[position].let { it2 ->
                if (holder.binding.btnAddToCart.visibility == View.VISIBLE) {
                    holder.binding.btnAddToCart.visibility = View.GONE
                    holder.binding.btnAddMinus.visibility = View.VISIBLE

                    var count = categoryData[position].minAddToCartQty!!.toInt()

                    if (holder.binding.btnAddMinus.visibility == View.VISIBLE) {
                        holder.binding.btnPlus.setOnClickListener {
                            ++count
                            holder.binding.tvItemTotal.text = count.toString()

                        }
                        holder.binding.btnMinus.setOnClickListener {

                            if (count >= 0) {
                                count--
                                holder.binding.tvItemTotal.text = count.toString()

                            }
                            if (count < 0) {
                                holder.binding.btnAddMinus.visibility = View.GONE
                                holder.binding.btnAddToCart.visibility = View.VISIBLE
                                holder.binding.tvItemTotal.text = "1"
                            }

                        }
                    }
                }
            }
        }*/

        /*Glide.with(context)
            .load(categoryData[position].thumbNail)
            .thumbnail(Glide.with(context).load("https://raw.githubusercontent.com/ybq/Android-SpinKit/master/art/Pulse.gif"))
            .fitCenter()
            .into(holder.binding.ivSubCategory)*/
    }



}

