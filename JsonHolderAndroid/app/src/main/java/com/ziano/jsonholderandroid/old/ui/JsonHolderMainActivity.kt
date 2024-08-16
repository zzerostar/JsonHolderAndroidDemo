package com.ziano.kotlinandroid.jsonholder.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ziano.jsonholderandroid.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/30
 * @desc
 */
@AndroidEntryPoint
class JsonHolderMainActivity : AppCompatActivity() {

    val bottomNavigationView: BottomNavigationView by lazy { findViewById(R.id.bottom_navigation_view) }

    lateinit var postFragment: PostListFragment
    lateinit var photoListFragment: PhotoListFragment
    lateinit var userListFragment: UserListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_holder_main)

        postFragment = PostListFragment()
        photoListFragment = PhotoListFragment()
        userListFragment = UserListFragment()

        bottomNavigationView.setOnItemSelectedListener {
            switchFragment(it.itemId)
            return@setOnItemSelectedListener true
        }

        switchFragment(R.id.bottom_menu_post)
    }

    fun switchFragment(itemId: Int) {
        when (itemId) {
            R.id.bottom_menu_post -> {
                val fm = supportFragmentManager
                fm.beginTransaction().replace(R.id.fragment_container, postFragment).commit()

            }

            R.id.bottom_menu_photo -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, photoListFragment).commit()
            }

            R.id.bottom_menu_user -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, userListFragment).commit()
            }
        }
    }
}