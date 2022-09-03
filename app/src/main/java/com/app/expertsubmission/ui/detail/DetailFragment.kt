package com.app.expertsubmission.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.app.expertsubmission.R
import com.app.expertsubmission.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedArticle = DetailFragmentArgs.fromBundle(arguments as Bundle).article
        viewModel.selectedArticle?.let { article ->
            with(binding) {
                Glide.with(requireContext())
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
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(requireContext(), com.app.core.R.drawable.ic_fav))
        } else {
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(requireContext(), com.app.core.R.drawable.ic_fav_border))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}