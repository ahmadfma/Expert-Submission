package com.app.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.favorite.R
import com.app.favorite.databinding.FragmentListFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFavoriteFragment : Fragment() {

    private var _binding: FragmentListFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}