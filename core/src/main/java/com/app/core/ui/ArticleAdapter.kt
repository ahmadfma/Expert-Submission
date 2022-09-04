package com.app.core.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.core.R
import com.app.core.databinding.ItemArticleBinding
import com.app.core.domain.model.Article
import com.app.core.utils.DateTime
import com.bumptech.glide.Glide

class ArticleAdapter(private val onClick: (Article) -> Unit): RecyclerView.Adapter<ArticleAdapter.Holder>() {
    private val listArticle = mutableListOf<Article>()
    private lateinit var context: Context

    inner class Holder(private val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) = with(binding) {
            Glide.with(this.root)
                .load(article.urlToImage)
                .placeholder(R.color.grey)
                .error(R.color.grey)
                .into(articleImage)
            articleTitle.text = article.title
            articleDesc.text = article.description
            articleSource.text = StringBuilder().append("${article.sourceName} - ${article.publishedAt?.let { DateTime.getPublishTime(it) }}")
            this.root.setOnClickListener {
                onClick(article)
            }

            when (context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    articleTitle.setTextColor(context.resources.getColor(R.color.white, null))
                    articleDesc.setTextColor(context.resources.getColor(R.color.white, null))
                    articleDesc.setTextColor(context.resources.getColor(R.color.white, null))
                    horizontalLine.setBackgroundColor(context.resources.getColor(R.color.black8, null))
                }
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(articles: List<Article>) {
        this.listArticle.clear()
        this.listArticle.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(listArticle[position])

    override fun getItemCount(): Int = listArticle.size

}