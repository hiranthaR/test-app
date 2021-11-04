package xyz.hirantha.testapplication.ui.post

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import xyz.hirantha.testapplication.data.repository.Repository
import xyz.hirantha.testapplication.extra.lazyDeferred
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
//    val post by lazyDeferred { repository.getPost(postId) }
}