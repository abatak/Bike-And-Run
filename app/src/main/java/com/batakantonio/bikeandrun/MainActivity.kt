package com.batakantonio.bikeandrun

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.batakantonio.bikeandrun.data.firebase.auth.SignInActivity
import com.batakantonio.bikeandrun.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener

class MainActivity : AppCompatActivity(), OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val bicycleGraph = R.navigation.nav_graph_bicycle
    private val runningGraph = R.navigation.nav_graph_running

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {

            supportActionBar?.hide()

            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment

            if (navHostFragment != null) {
                 navController = navHostFragment.navController

                bottomNav.setOnItemSelectedListener(this@MainActivity)

                navController.setGraph(bicycleGraph)

                setContentView(root)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ic_bike -> {
                navController.setGraph(bicycleGraph)
                true
            }
            R.id.ic_running -> {
                navController.setGraph(runningGraph)
                true
            }

            else -> {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
                true
            }
        }
    }


}