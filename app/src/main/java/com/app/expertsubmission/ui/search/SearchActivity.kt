package com.app.expertsubmission.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.core.data.Resource
import com.app.core.ui.ArticleAdapter
import com.app.expertsubmission.databinding.ActivitySearchBinding
import com.app.expertsubmission.ui.detail.DetailActivity
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private var keyword = ""
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initVariable()
        initListener()
    }

    private fun initVariable() = with(binding) {
        articleAdapter = ArticleAdapter {
            Intent().apply {
                setClass(this@SearchActivity, DetailActivity::class.java)
                putExtra(DetailActivity.ARTICLE, it)
                startActivity(this)
            }
        }
        rvArticles.setHasFixedSize(true)
        rvArticles.layoutManager = LinearLayoutManager(this@SearchActivity)
        rvArticles.adapter = articleAdapter
        keyword = intent.getStringExtra(KEYWORD).toString()
        keywordTv.text = keyword
    }

    private fun initListener() = lifecycleScope.launch {
        viewModel.searchArticle(keyword.trim()).observe(this@SearchActivity) {
            when(it) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showEmpty(true)
                    Toast.makeText(this@SearchActivity, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    showLoading(false)
                    val data = it.data
                    if(data != null && data.isNotEmpty()) {
                        showEmpty(false)
                        showData()
                        articleAdapter.submitList(data)
                    } else {
                        showEmpty(true)
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) = with(binding) {
        if(isLoading) {
            progressBar.visibility = View.VISIBLE
            emptyInfoSearch.root.visibility = View.GONE
            rvArticles.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun showEmpty(isEmpty: Boolean) = with(binding) {
        if(isEmpty) {
            emptyInfoSearch.root.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            rvArticles.visibility = View.GONE
        } else {
            emptyInfoSearch.root.visibility = View.GONE
        }
    }

    private fun showData() = with(binding) {
        emptyInfoSearch.root.visibility = View.GONE
        progressBar.visibility = View.GONE
        rvArticles.visibility = View.VISIBLE
    }

    companion object {
        const val KEYWORD = "keyword"
    }
}