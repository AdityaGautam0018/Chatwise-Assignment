package com.example.products

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(
    private val context: Context,
    private val productsList: ProductsList?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_TITLE = 0
        const val TYPE_PRODUCT = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_TITLE else TYPE_PRODUCT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TITLE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_title, parent, false)
                TitleViewHolder(view)
            }
            TYPE_PRODUCT -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
                ProductViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_TITLE -> (holder as TitleViewHolder).bind()
            TYPE_PRODUCT -> {
                val product = productsList?.products?.get(position - 1)
                (holder as ProductViewHolder).bind(product)
                holder.itemView.setOnClickListener {
                    val fragment = ProductDetailFragment().apply {
                        arguments = Bundle().apply {
                            putSerializable("product_key", product)
                        }
                    }
                    (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.main, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return (productsList?.products?.size ?: 0) + 1 // +1 for the title
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)

        fun bind() {
            titleTextView.text = "Products List"
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImageView: ImageView = itemView.findViewById(R.id.product_img)
        private val productTitleTextView: TextView = itemView.findViewById(R.id.product_title)
        private val productDescriptionTextView: TextView = itemView.findViewById(R.id.product_description)

        fun bind(product: ProductsList.Product?) {
            productTitleTextView.text = product?.title ?: "No title"
            productDescriptionTextView.text = product?.description ?: "No description"
            Glide.with(itemView.context)
                .load(product?.thumbnail)
                .into(productImageView)
        }
    }
}
