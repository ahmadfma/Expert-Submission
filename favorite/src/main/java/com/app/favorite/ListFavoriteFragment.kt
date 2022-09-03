package com.app.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.core.data.Resource
import com.app.core.ui.ArticleAdapter
import com.app.expertsubmission.ui.detail.DetailActivity
import com.app.favorite.databinding.FragmentListFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class ListFavoriteFragment : Fragment() {

    private var _binding: FragmentListFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListFavoriteViewModel by viewModel()
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        loadKoinModules(favoriteModule)
        _binding = FragmentListFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleAdapter = ArticleAdapter {
            Intent().apply {
                setClass(requireActivity(), DetailActivity::class.java)
                putExtra(DetailActivity.ARTICLE, it)
                startActivity(this)
            }
        }
        viewModel.getFavoriteArticles().observe(viewLifecycleOwner) {
            articleAdapter.setData(it)
        }
        with(binding) {
            rvArticles.setHasFixedSize(true)
            rvArticles.layoutManager = LinearLayoutManager(requireContext())
            rvArticles.adapter = articleAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}