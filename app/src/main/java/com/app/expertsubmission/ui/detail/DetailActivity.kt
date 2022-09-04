package com.app.expertsubmission.ui.detail

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.app.core.R
import com.app.core.domain.model.Article
import com.app.core.utils.DataMapper
import com.app.core.utils.DateTime
import com.app.expertsubmission.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(com.app.expertsubmission.R.string.detail)
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
                articleSource.text = StringBuilder(article.sourceName.toString()).append(" - ${article.publishedAt?.let {
                    DateTime.getPublishTime(it)
                }}")
                setStatusFavorite(article)
                favBtn.setOnClickListener {
                    article.isFavorite = !article.isFavorite
                    viewModel.setFavoriteArticle(article, article.isFavorite)
                    setStatusFavorite(article)
                }
            }
        }
        bacaSelengkapnyaBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(viewModel.selectedArticle?.url)
            startActivity(intent)
        }
        when (this@DetailActivity.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                articleTitle.setTextColor(this@DetailActivity.resources.getColor(R.color.white, null))
                articleSource.setTextColor(this@DetailActivity.resources.getColor(R.color.grey, null))
                articleContent.setTextColor(this@DetailActivity.resources.getColor(R.color.white, null))
            }
        }
    }

    private fun setStatusFavorite(article: Article) = lifecycleScope.launch {
        var item = article
        val result = viewModel.getArticleByTitle(article.title)
        if(result.isEmpty()) {
            Log.d("Favorite", "result: ${item.title} EMPTY")
            viewModel.insertArticle(DataMapper.mapDomainToEntity(article))
        } else {
            Log.d("Favorite", "result: ${item.title} NOT EMPTY")
            val loadedArticle = DataMapper.mapEntitiesToDomain(result).first()
            item = loadedArticle
            item.isFavorite = article.isFavorite
        }
        if (item.isFavorite) {
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_fav))
        } else {
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.ic_fav_border))
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