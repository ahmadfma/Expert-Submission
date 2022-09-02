package com.app.expertsubmission.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
        with(binding) {
            Glide.with(requireContext())
                .load(viewModel.selectedArticle?.urlToImage)
                .into(articleImage)
            articleTitle.text = viewModel.selectedArticle?.title
            articleContent.text = viewModel.selectedArticle?.content
            articleSource.text = viewModel.selectedArticle?.sourceName
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}