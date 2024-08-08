package com.example.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductListFragment: Fragment() {

    lateinit var rvProduct : RecyclerView
    lateinit var productAdapter: ProductAdapter



    var BASEURL = "https://dummyjson.com"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fetchProductData()
        val view = inflater.inflate(R.layout.product_list, container, false)
        rvProduct = view.findViewById(R.id.rv_products)
        rvProduct.layoutManager = LinearLayoutManager(requireContext())
        return view


    }
    private fun fetchProductData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        var retroData = retrofit.getProductData()

        retroData.enqueue(object : Callback<ProductsList> {
            override fun onResponse(p0: Call<ProductsList>, p1: Response<ProductsList>) {
                var data = p1.body()
                productAdapter = ProductAdapter(requireContext(), data)
                rvProduct.adapter = productAdapter
                Log.d("Productshow", data.toString())
            }

            override fun onFailure(p0: Call<ProductsList>, p1: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}