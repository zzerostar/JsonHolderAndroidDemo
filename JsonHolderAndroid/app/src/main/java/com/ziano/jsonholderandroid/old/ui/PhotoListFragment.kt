package com.ziano.kotlinandroid.jsonholder.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ziano.jsonholderandroid.databinding.FragmentPhotoListBinding
import com.ziano.jsonholderandroid.jsonholder.old.base.BaseBindingFragment
import com.ziano.jsonholderandroid.old.vm.PhotoListViewModel
import com.ziano.jsonholderandroid.old.vm.State
import com.ziano.kotlinandroid.jsonholder.ui.adapter.PhotoRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/6/25
 * @desc
 */
@AndroidEntryPoint
class PhotoListFragment : BaseBindingFragment<PhotoListViewModel, FragmentPhotoListBinding>(FragmentPhotoListBinding::inflate) {

    override val mViewModel: PhotoListViewModel by viewModels<PhotoListViewModel>()

    val photoRecyclerAdapter by lazy { PhotoRecyclerAdapter(this@PhotoListFragment.requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observePosts()
        fetchData()

    }

    private fun initView() {
        mViewBinding.run {
            this.recyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = photoRecyclerAdapter
            }
        }
    }

    private fun observePosts() {
        lifecycleScope.launch(exceptionHandler) {
            mViewModel.photos.collect { state ->
                when (state) {
                    is State.Loading -> showLoading()
                    is State.Error -> showError(state.errorMsg)
                    is State.Success -> {
                        endLoading()
                        photoRecyclerAdapter.submitList(state.data)
                    }
                }
            }
        }
    }

    override fun fetchData() {
        mViewModel.getPhotos()
    }

}