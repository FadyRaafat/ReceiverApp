package com.tocaan.receiver.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tocaan.receiver.R
import com.tocaan.receiver.api.models.User
import com.tocaan.receiver.databinding.ActivityMainBinding
import com.tocaan.receiver.ui.adapters.UsersAdapter
import com.tocaan.receiver.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel.startServer()
        binding.showUsersIV.setOnClickListener {
            mainViewModel.getAllUsers().observe(this, { users ->
                if (!users.isNullOrEmpty()) {
                    initUserRecycler(users)
                }
            })
        }
    }

    private fun initUserRecycler(users: List<User>) {
        val adapter = UsersAdapter()
        binding.userRV.adapter = adapter
        adapter.submitList(users)
    }


}