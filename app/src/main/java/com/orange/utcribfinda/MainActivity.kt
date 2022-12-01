package com.orange.utcribfinda

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.orange.utcribfinda.databinding.ActivityMainBinding
import com.orange.utcribfinda.ui.home.HomeFragment
import com.orange.utcribfinda.ui.saved.ResultsFragment
import com.orange.utcribfinda.ui.saved.SavedFragment

class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding

    private fun addHomeFragment() {
        // No back stack for home
        supportFragmentManager.commit {
            add(R.id.main_frame, HomeFragment.newInstance())
            addToBackStack(null)
            // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    private fun addResultsFragment() {
        // No back stack for home
        supportFragmentManager.commit {
            add(R.id.main_frame, ResultsFragment.newInstance())
            addToBackStack(null)
            // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    private fun addSavedFragment() {
        // No back stack for home
        supportFragmentManager.commit {
            add(R.id.main_frame, SavedFragment.newInstance())
            addToBackStack(null)
            // TRANSIT_FRAGMENT_FADE calls for the Fragment to fade away
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        //setContentView(binding.root)

        val navView: BottomNavigationView = activityMainBinding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home,
            R.id.navigation_results, R.id.navigation_saved))
        setupActionBarWithNavController(navController, appBarConfiguration)
        // set icon for the bar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.mipmap.ic_launcher_round)



//        activityMainBinding.navView.setOnClickListener {
//            it.onNavDestinationSelected(navController)
//        }


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

       // addHomeFragment()



    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // https://developer.android.com/guide/navigation/navigation-ui
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

}