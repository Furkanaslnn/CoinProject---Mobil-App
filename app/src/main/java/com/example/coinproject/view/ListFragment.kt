package com.example.coinproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinproject.adapter.CoinRecyclerAdapter
import com.example.coinproject.databinding.FragmentListBinding
import com.example.coinproject.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ListViewModel
    private val coinRecyclerAdapter = CoinRecyclerAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.refreshData()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = coinRecyclerAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.errorMessege.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.refreshFromInternet()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.coins.observe(viewLifecycleOwner) { coins ->
            coinRecyclerAdapter.updateCoinList(coins)
            binding.recyclerView.visibility = View.VISIBLE

        }

        viewModel.coinErrorMessage.observe(viewLifecycleOwner) {
            if (it) {
                binding.errorMessege.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.errorMessege.visibility = View.GONE
            }
        }
        viewModel.coinLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.errorMessege.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}