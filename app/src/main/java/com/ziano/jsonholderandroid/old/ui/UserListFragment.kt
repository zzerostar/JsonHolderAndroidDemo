package com.ziano.kotlinandroid.jsonholder.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziano.jsonholderandroid.databinding.FragmentUsersBinding
import com.ziano.jsonholderandroid.jsonholder.old.base.BaseBindingFragment
import com.ziano.jsonholderandroid.old.vm.State
import com.ziano.jsonholderandroid.old.vm.UserListViewModel
import com.ziano.kotlinandroid.jsonholder.ui.adapter.UserRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/16
 * @desc
 */
@AndroidEntryPoint
class UserListFragment : BaseBindingFragment<UserListViewModel, FragmentUsersBinding>(FragmentUsersBinding::inflate) {

    override val mViewModel: UserListViewModel by viewModels<UserListViewModel>()

    val userRecyclerAdapter: UserRecyclerAdapter by lazy { UserRecyclerAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe()
        fetchData()
    }

    fun initView() {
        mViewBinding.run {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = userRecyclerAdapter
            recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        }
    }

    fun observe() {
        lifecycleScope.launch(exceptionHandler) {
            mViewModel.users.collect {
                when (it) {
                    is State.Loading -> showLoading()
                    is State.Error -> showError()
                    is State.Success -> {
                        endLoading()
                        userRecyclerAdapter.submitList(it.data)
                    }
                }
            }
        }
    }

    override fun fetchData() {
        mViewModel.getUserList()
    }
}