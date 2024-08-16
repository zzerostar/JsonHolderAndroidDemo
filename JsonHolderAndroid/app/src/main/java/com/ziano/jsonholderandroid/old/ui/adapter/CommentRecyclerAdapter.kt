package com.ziano.kotlinandroid.jsonholder.ui.adapter

import com.ziano.jsonholderandroid.R
import com.ziano.jsonholderandroid.common.data.model.Comment
import com.ziano.jsonholderandroid.databinding.CardviewCommentItemBinding
import com.ziano.jsonholderandroid.jsonholder.old.base.BaseAdapter


/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/29
 * @desc
 */
class CommentRecyclerAdapter(val onItemClick: ((Comment)-> Unit)?) : BaseAdapter<CardviewCommentItemBinding, Comment>() {

    override val layoutId: Int = R.layout.cardview_comment_item

    override fun bind(binding: CardviewCommentItemBinding, item: Comment) {
        binding.apply{
            comment = item
            executePendingBindings()
            tvName.setText(String.format("ID: %d", item.id))

            binding.root.setOnClickListener {
                onItemClick?.apply {
                    invoke(item)
                }
            }
        }

    }

}