package com.ziano.jsonholderandroid.jsonholder.old.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/25
 * @desc
 */
class BaseViewHolder<BINDING : ViewDataBinding>(val binding: BINDING) :
    RecyclerView.ViewHolder(binding.root) {
}