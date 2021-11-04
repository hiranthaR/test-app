package xyz.hirantha.testapplication.ui.posts

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import xyz.hirantha.testapplication.data.repository.Repository
import xyz.hirantha.testapplication.extra.lazyDeferred
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val posts by lazyDeferred { repository.getPosts() }
}