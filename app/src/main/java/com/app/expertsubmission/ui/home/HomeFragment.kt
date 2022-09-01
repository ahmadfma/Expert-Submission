package com.app.expertsubmission.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.expertsubmission.R
import com.app.expertsubmission.core.data.Resource
import com.app.expertsubmission.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.topArticles.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Loading -> {
                    Log.d(TAG, "topArticles: Loading")
                }
                is Resource.Error -> {
                    Log.e(TAG, "topArticles: error = ${it.message}")
                }
                is Resource.Success -> {
                    Log.d(TAG, "topArticles: ${it.data}")
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val TAG = "HomeFragment"
    }

}