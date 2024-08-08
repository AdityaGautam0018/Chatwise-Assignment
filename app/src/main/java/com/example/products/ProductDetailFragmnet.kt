package com.example.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class ProductDetailFragment: Fragment() {
    private lateinit var product: ProductsList.Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = it.getSerializable("product_key") as ProductsList.Product
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.product_detail, container, false)
        val productTitle :TextView = view.findViewById(R.id.product_title_dt)
        val productDescription :TextView = view.findViewById(R.id.product_description_dt)
        val productCategory: TextView = view.findViewById(R.id.product_category_dt)
        val productPrice: TextView = view.findViewById(R.id.product_price_dt)
        val productDiscount: TextView = view.findViewById(R.id.product_discount_dt)
        val productRating: TextView = view.findViewById(R.id.product_rating_dt)
        val productStock: TextView = view.findViewById(R.id.product_stock_dt)
        val productBrand: TextView = view.findViewById(R.id.product_brand_dt)
        val productWarrantyInformation: TextView = view.findViewById(R.id.product_warrantyInformation_dt)
        val productShippingInformation: TextView = view.findViewById(R.id.product_shippingInformation_dt)
        val productImage: ImageView = view.findViewById(R.id.product_img_dt)
        productTitle.text = product.title
        productDescription.text = product.description
        productCategory.text = "Category: ${product.category}"
        productPrice.text = "Price: ${product.price}"
        productDiscount.text = "Discount: ${product.discountPercentage}%"
        productRating.text= "Rating: ${product.rating}"
        productStock.text = "Stock: ${product.stock}"
        productBrand.text = "Brand: ${product.brand}"
        productWarrantyInformation.text ="Warranty: ${product.warrantyInformation}"
        productShippingInformation.text = "Shipping: ${product.shippingInformation}"

        Glide.with(requireContext())
            .load(product.thumbnail)
            .into(productImage)

        return view
    }
}