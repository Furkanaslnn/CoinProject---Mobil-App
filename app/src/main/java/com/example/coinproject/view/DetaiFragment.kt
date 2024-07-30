package com.example.coinproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coinproject.databinding.FragmentDetaiBinding
import com.example.coinproject.util.doPlaceHolder
import com.example.coinproject.util.downloadImage
import com.example.coinproject.viewmodel.DetailViewModel
import kotlinx.coroutines.launch

class DetaiFragment : Fragment() {

    private var _binding: FragmentDetaiBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : DetailViewModel
    var coinId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetaiBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this) [DetailViewModel::class.java]

        arguments?.let {
            coinId = DetaiFragmentArgs.fromBundle(it).id.toInt()

        }

        viewModel.getRoomData(coinId)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.coinLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.name.text = it.name
                binding.symbol.text = it.symbol
                binding.price.text = it.currentPrice.toString()
                it.image?.let { it1 ->
                    binding.imageView2.downloadImage(it1, doPlaceHolder(requireContext()))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}