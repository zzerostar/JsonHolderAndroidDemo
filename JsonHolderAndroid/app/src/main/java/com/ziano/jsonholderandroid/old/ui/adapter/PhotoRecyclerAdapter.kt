package com.ziano.kotlinandroid.jsonholder.ui.adapter

import android.content.Context
import android.widget.FrameLayout
import coil.load
import com.ziano.jsonholderandroid.R
import com.ziano.jsonholderandroid.common.data.model.Photo
import com.ziano.jsonholderandroid.databinding.CardviewPhotoBinding
import com.ziano.jsonholderandroid.jsonholder.old.base.BaseAdapter
import com.ziano.kotlinandroid.utils.DeviceUtil

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/6/25
 * @desc
 */
class PhotoRecyclerAdapter(val context: Context) : BaseAdapter<CardviewPhotoBinding, Photo>() {

    override val layoutId: Int = R.layout.cardview_photo

    override fun bind(binding: CardviewPhotoBinding, item: Photo) {
        binding.apply {

            val imgSize = DeviceUtil.getScreenWidth() / 2

            ivImg.layoutParams = FrameLayout.LayoutParams(imgSize, imgSize)
            ivImg.load(item.url) {
                placeholder(R.mipmap.icon_empty)
                crossfade(true)
            }
        }
    }
}