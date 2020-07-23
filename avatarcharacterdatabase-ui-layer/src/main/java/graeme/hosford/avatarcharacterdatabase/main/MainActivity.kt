package graeme.hosford.avatarcharacterdatabase.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import graeme.hosford.avatarcharacterdatabase.R
import graeme.hosford.avatarcharacterdatabase.common.view.activity.BaseActivity
import graeme.hosford.avatarcharacterdatabase.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.main_nav_host_fragment)
        val appBarConfig = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfig)
    }
}