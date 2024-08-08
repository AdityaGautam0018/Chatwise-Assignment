package com.example.products
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ButtonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_button, container, false)
        val viewProductsButton: Button = view.findViewById(R.id.view_products_btn)

        viewProductsButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ProductListFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}