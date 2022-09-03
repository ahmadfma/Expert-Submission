package com.app.expertsubmission.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.app.core.R
import com.app.expertsubmission.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail"
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        viewModel.selectedArticle = intent.getParcelableExtra(ARTICLE)
        viewModel.selectedArticle?.let { article ->
            with(binding) {
                Glide.with(this@DetailActivity)
                    .load(article.urlToImage)
                    .into(articleImage)
                articleTitle.text = article.title
                articleContent.text = article.content
                articleSource.text = article.sourceName
                var statusFavorite = article.isFavorite
                setStatusFavorite(statusFavorite)
                favBtn.setOnClickListener {
                    statusFavorite = !statusFavorite
                    viewModel.setFavoriteArticle(article, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fav))
        } else {
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fav_border))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ARTICLE = "article"
    }

}