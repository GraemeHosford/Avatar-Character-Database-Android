package graeme.hosford.avatarcharacterdatabase.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import graeme.hosford.avatarcharacterdatabase.R
import graeme.hosford.avatarcharacterdatabase.databinding.ActivityMainBinding
import graeme.hosford.avatarcharacterdatabase.ui.common.view.activity.BaseActivity

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