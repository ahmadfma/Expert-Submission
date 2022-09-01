package com.app.expertsubmission.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.expertsubmission.core.data.Resource
import com.app.expertsubmission.core.ui.ArticleAdapter
import com.app.expertsubmission.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleAdapter = ArticleAdapter {
            Toast.makeText(requireContext(), it.title, Toast.LENGTH_SHORT).show()
        }

        with(binding) {
            rvArticles.setHasFixedSize(true)
            rvArticles.layoutManager = LinearLayoutManager(requireContext())
            rvArticles.adapter = articleAdapter
        }

        viewModel.topArticles.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Loading -> {
                    showProgressBar(true)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "topArticles: error = ${it.message}")
                }
                is Resource.Success -> {
                    showProgressBar(false)
                    it.data?.let { it1 -> articleAdapter.setData(it1) }
                }
            }
        }
    }

    private fun showProgressBar(isLoading: Boolean) = with(binding) {
        if(isLoading) {
            rvArticles.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        } else {
            rvArticles.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
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