package com.app.expertsubmission

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.app.core.data.Resource
import com.app.expertsubmission.databinding.ActivityMainBinding
import com.app.expertsubmission.ui.home.HomeFragment
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_favorite), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    searchArticle(p0)
                }
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean = true
        })

        navController.addOnDestinationChangedListener { _, destination, _ ->
            menu.findItem(R.id.action_search).actionView.isVisible = destination.label.toString() != getString(R.string.favorite)
        }

        return true
    }

    private fun searchArticle(keyword: String) =  lifecycleScope.launch {
        Log.d("MainActivity", "searchArticle: all")
        viewModel.searchArticle(keyword).observe(this@MainActivity) {
            when(it) {
                is Resource.Loading -> {}
                is Resource.Error -> {
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    val data = it.data
                    if(data != null && data.isNotEmpty()) {
                        HomeFragment.articleAdapter.setData(data)
                    } else {
                        Toast.makeText(this@MainActivity, "Tidak Ditemukan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_settings -> {
                val asd = findNavController(R.id.nav_host_fragment_content_main).currentDestination
                Log.d("MainActivity", "currentDestination: $asd")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}