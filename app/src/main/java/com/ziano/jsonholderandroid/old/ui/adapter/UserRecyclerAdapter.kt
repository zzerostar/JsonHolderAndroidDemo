package com.ziano.kotlinandroid.jsonholder.ui.adapter

import com.ziano.jsonholderandroid.R
import com.ziano.jsonholderandroid.common.data.model.User
import com.ziano.jsonholderandroid.databinding.CardviewUserItemBinding
import com.ziano.jsonholderandroid.jsonholder.old.base.BaseAdapter

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/16
 * @desc
 */
class UserRecyclerAdapter: BaseAdapter<CardviewUserItemBinding, User>() {

    override val layoutId: Int = R.layout.cardview_user_item
    override fun bind(binding: CardviewUserItemBinding, item: User) {
        binding.apply {
            tvName.setText(item.name)
            tvEmail.setText(item.email)
            tvPhone.setText(item.phone)
            tvCompany.setText(item.company.name)
            tvLocation.setText("${item.address.street} ${item.address.suite} ${item.address.city} ")
        }
    }
}