package com.orange.utcribfinda

import android.os.Bundle
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.orange.utcribfinda.databinding.ActivityMainBinding
import com.orange.utcribfinda.ui.saved.SavedFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    fun launchSaved() {
//        supportActionBar?.
//    }

    private fun addSavedFragment() {
        // No back stack for home
        supportFragmentManager.commit {
            add(R.id.main_frame, SavedFragment.newInstance(), "savedFragTag")
            addToBackStack(null)
            // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // set icon for the bar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher_round)
//        method below changes name based on fragment
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_saved
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)


        navView.setupWithNavController(navController)

        

//        binding.navView.setOnClickListener {
//            supportFragmentManager.commit {
//                addSavedFragment()
//            }
//        }


    }

}