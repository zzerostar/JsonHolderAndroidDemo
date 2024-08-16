package com.ziano.kotlinandroid.jsonholder.ui.adapter

import com.ziano.jsonholderandroid.R
import com.ziano.jsonholderandroid.common.data.model.Post
import com.ziano.jsonholderandroid.databinding.CardviewPostItemBinding
import com.ziano.jsonholderandroid.jsonholder.old.base.BaseAdapter

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/25
 * @desc
 */
class PostRecyclerAdapter(val onItemClick: (Post)-> Unit) : BaseAdapter<CardviewPostItemBinding, Post>() {

    override val layoutId: Int = R.layout.cardview_post_item

    override fun bind(binding: CardviewPostItemBinding, item: Post) {
        binding.apply{
            post = item
            executePendingBindings()
            tvName.setText(String.format("ID: %d", item.id))

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }

    }

}