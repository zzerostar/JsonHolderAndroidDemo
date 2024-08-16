package com.ziano.jsonholderandroid.jsonholder.old.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/25
 * @desc
 */
abstract class BaseAdapter<BINDING : ViewDataBinding, T>(var data: MutableList<T> = mutableListOf()) :
    RecyclerView.Adapter<BaseViewHolder<BINDING>>() {

    abstract val layoutId: Int

    abstract fun bind(binding: BINDING, item: T)

    fun submitList(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun appendList(data: List<T>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BINDING> {
        val binder = DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )

        return BaseViewHolder(binder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BINDING>, position: Int) {
        bind(holder.binding, data[position])
    }

    override fun getItemCount(): Int = this.data.size

}