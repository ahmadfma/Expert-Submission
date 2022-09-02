package com.app.expertsubmission.ui.detail

import androidx.lifecycle.ViewModel
import com.app.core.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    var selectedArticle: Article? = null
}