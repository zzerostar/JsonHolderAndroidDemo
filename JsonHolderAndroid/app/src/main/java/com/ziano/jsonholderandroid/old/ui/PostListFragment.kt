package com.ziano.kotlinandroid.jsonholder.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.ziano.jsonholderandroid.common.data.model.Post
import com.ziano.jsonholderandroid.databinding.FragmentPostListBinding
import com.ziano.jsonholderandroid.jsonholder.old.base.BaseBindingFragment
import com.ziano.jsonholderandroid.old.vm.ListViewState
import com.ziano.jsonholderandroid.old.vm.PostListViewModel
import com.ziano.kotlinandroid.jsonholder.constants.ExtraKey
import com.ziano.kotlinandroid.jsonholder.ui.adapter.PostRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/30
 * @desc
 */
@AndroidEntryPoint
class PostListFragment : BaseBindingFragment<PostListViewModel, FragmentPostListBinding>
    (FragmentPostListBinding::inflate) {

    override val mViewModel: PostListViewModel by viewModels<PostListViewModel>()

    val postRecyclerAdapter: PostRecyclerAdapter = PostRecyclerAdapter(::onItemClicked)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observePosts()
        fetchData()
    }

    private fun observePosts() {
        lifecycleScope.launch(exceptionHandler) {
            mViewModel.postlist.collect { state ->
                when (state) {
                    is ListViewState.Loading -> showLoading()
                    is ListViewState.Error -> showError(state.errorMsg)
                    is ListViewState.Success -> {
                        endLoading()
                        mViewBinding.refreshLayout.finishRefresh()
                        postRecyclerAdapter.submitList(state.data)
                    }

                    is ListViewState.LoadMore -> {
                        mViewBinding.refreshLayout.finishLoadMore()
                        postRecyclerAdapter.appendList(state.data)
                    }
                }
            }
        }
    }

    private fun initView() {
        mViewBinding.run {
            this.postRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = postRecyclerAdapter
                this.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            }

            this.refreshLayout.apply {
                setRefreshHeader(ClassicsHeader(this@PostListFragment.requireContext()))
                setRefreshFooter(ClassicsFooter(this@PostListFragment.requireContext()))
                setOnRefreshListener {
                    mViewModel.refresh()
                    mViewBinding.refreshLayout.finishRefresh(5000, false, false)
                }
                setOnLoadMoreListener {
                    mViewModel.loadMore()
                }

            }
        }
    }

    override fun fetchData() {
        mViewModel.refresh()
    }

    private fun onItemClicked(post: Post) {
        val intent = Intent(requireContext(), PostDetailActivity::class.java)
        intent.putExtra(ExtraKey.POST_ID, post.id)
        startActivity(intent)
    }


}