package com.mageshr2494.restaurant.ui.fragments

import com.mageshr2494.restaurant.databinding.FragmentMyCartBinding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mageshr2494.restaurant.CartHandle
import com.mageshr2494.restaurant.datasource.entities.StartersItem
import com.mageshr2494.restaurant.ui.adapters.StartersAdapter

class MyCartFragment : Fragment(), StartersAdapter.ViewCartListener {

    private var myCartBinding: FragmentMyCartBinding? = null
    private lateinit var startersAdapter: StartersAdapter
    private lateinit var starters: List<StartersItem>
    private var showMoreOption: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myCartBinding = FragmentMyCartBinding.inflate(inflater, container, false)

        setupRecyclerView()
        setOnClickListenerFun()
        getTotalAmount()

        return myCartBinding!!.root
    }

    private fun setupRecyclerView() {
        startersAdapter = StartersAdapter(requireContext(), this, true)
        myCartBinding!!.myCartRecyclerView.layoutManager =
            LinearLayoutManager(context)
        myCartBinding!!.myCartRecyclerView.adapter = startersAdapter

        getMycart()
    }

    private fun getMycart() {
        starters = CartHandle.getMyCart(requireContext())

        if (starters.size > 2 && showMoreOption) {
            myCartBinding!!.showMore.visibility = View.VISIBLE
            var startersLimit: List<StartersItem> = starters.take(2)
            startersAdapter.submitList(startersLimit)
        } else {
            myCartBinding!!.showMore.visibility = View.GONE
            startersAdapter.submitList(starters)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myCartBinding!!.imageView3.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun setOnClickListenerFun() {

        myCartBinding!!.linearLayoutRadio1.setOnClickListener {
            myCartBinding!!.radioButton1.isChecked = true
            myCartBinding!!.radioButton2.isChecked = false
        }

        myCartBinding!!.linerLayoutRadio2.setOnClickListener {
            myCartBinding!!.radioButton1.isChecked = false
            myCartBinding!!.radioButton2.isChecked = true
        }

        myCartBinding!!.showMore.setOnClickListener {
            showMoreOption = false
            myCartBinding!!.showMore.visibility = View.GONE
            startersAdapter.submitList(starters)
        }
    }

    fun getTotalAmount() {
        val totalAmount = CartHandle.getMyCartAmount(requireContext())
        myCartBinding!!.textView14.text = "\u20ac" + totalAmount
    }

    override fun onClickedCount(count: Int) {
        Log.v("onClickedCount", "" + count)

        getTotalAmount()

        if (count == 0) {
            getMycart()
        }
    }
}