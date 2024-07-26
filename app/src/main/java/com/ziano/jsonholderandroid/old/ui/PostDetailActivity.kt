package com.ziano.kotlinandroid.jsonholder.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziano.jsonholderandroid.databinding.ActivityPostDetailBinding
import com.ziano.jsonholderandroid.jsonholder.old.base.BaseBindingActivity
import com.ziano.jsonholderandroid.old.vm.PostDetailViewModel
import com.ziano.jsonholderandroid.old.vm.State
import com.ziano.kotlinandroid.jsonholder.constants.ExtraKey
import com.ziano.kotlinandroid.jsonholder.ui.adapter.CommentRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/26
 * @desc
 */
@AndroidEntryPoint
class PostDetailActivity : BaseBindingActivity<PostDetailViewModel, ActivityPostDetailBinding>(ActivityPostDetailBinding::inflate) {

    var postId: Int = 0;

    val commentRecyclerAdapter: CommentRecyclerAdapter = CommentRecyclerAdapter(null)

    override val mViewModel by viewModels<PostDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        postId = intent.getIntExtra(ExtraKey.POST_ID, 0)
        observePostDetail()
        fetchData()
    }

    private fun initView() {
        mViewBinding.apply {
            commentRecyclerview.apply {
                layoutManager = LinearLayoutManager(this@PostDetailActivity)
                adapter = commentRecyclerAdapter
                addItemDecoration(DividerItemDecoration(this@PostDetailActivity, DividerItemDecoration.VERTICAL))
            }
        }
    }

    private fun observePostDetail() {
        lifecycleScope.launch(exceptionHandler) {
            mViewModel.postDetail.collect { state ->

                when (state) {
                    is State.Loading -> showLoading()
                    is State.Error -> showError(errorMsg = state.errorMsg)
                    is State.Success -> {
                        endLoading()
                        val post = state.data
                        mViewBinding.apply {
                            tvName.setText("ID: ${post.id}")
                            tvTitle.setText(post.title)
                            tvBody.setText(post.content)
                        }
                        mViewModel.getPostComments(post.id)
                    }

                }
            }
        }

        lifecycleScope.launch {
            mViewModel.comments.collect { state ->
                when (state) {
                    is State.Success -> {
                        commentRecyclerAdapter.submitList(state.data)
                    }

                    is State.Error -> {}
                    is State.Loading -> {}
                }
            }
        }
    }

    override fun fetchData() {
        mViewModel.getPostDetail(postId)
    }


}