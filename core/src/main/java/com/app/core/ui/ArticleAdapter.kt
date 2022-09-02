package com.app.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.core.databinding.ItemArticleBinding
import com.app.core.domain.model.Article
import com.bumptech.glide.Glide

class ArticleAdapter(private val onClick: (Article) -> Unit): RecyclerView.Adapter<ArticleAdapter.Holder>() {
    private val listArticle = mutableListOf<Article>()

    inner class Holder(private val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) = with(binding) {
            Glide.with(this.root)
                .load(article.urlToImage)
                .into(articleImage)
            articleTitle.text = article.title
            articleDesc.text = article.description
            articleSource.text = StringBuilder().append("${article.sourceName} - ${article.publishedAt}")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(articles: List<Article>) {
        this.listArticle.clear()
        this.listArticle.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(listArticle[position])

    override fun getItemCount(): Int = listArticle.size

}