package com.mageshr2494.restaurant.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mageshr2494.restaurant.CartHandle
import com.mageshr2494.restaurant.R
import com.mageshr2494.restaurant.databinding.FragmentBookATableBinding
import com.mageshr2494.restaurant.ui.adapters.StartersAdapter
import com.mageshr2494.restaurant.viewmodels.RestaurantViewmodel

class BookATableFragment : Fragment(), StartersAdapter.ViewCartListener {

    private var fragmentBookATableBinding: FragmentBookATableBinding? = null
    private lateinit var startersAdapter: StartersAdapter
    private val viewModel: RestaurantViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBookATableBinding = FragmentBookATableBinding.inflate(inflater, container, false)
        return fragmentBookATableBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadData()
        getTotalCount()

        fragmentBookATableBinding!!.viewCartLinearLayout.setOnClickListener {
            findNavController().navigate(R.id.myCartFragment)
        }
    }

    private fun setupRecyclerView() {
        startersAdapter = StartersAdapter(requireContext(), this, false)

        fragmentBookATableBinding!!.startersRecyclerview.layoutManager =
            LinearLayoutManager(context)

        fragmentBookATableBinding!!.startersRecyclerview.adapter = startersAdapter

        loadData()
    }

    private fun loadData() {
        val starters = viewModel.loadStartersData(requireContext())
        startersAdapter.submitList(starters)
    }

    override fun onClickedCount(count: Int) {
        getTotalCount()
    }

    fun getTotalCount() {
        val totalCount = CartHandle.getCartTotalItem(requireContext())
        fragmentBookATableBinding!!.viewCartText.text = "View cart ($totalCount items)"
    }
}