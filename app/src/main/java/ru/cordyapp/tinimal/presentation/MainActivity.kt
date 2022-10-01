package ru.cordyapp.tinimal.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ru.cordyapp.tinimal.R
import ru.cordyapp.tinimal.databinding.ActivityMainBinding
import ru.cordyapp.tinimal.presentation.login.LoginFragment
import ru.cordyapp.tinimal.utils.SharedPref

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHost = findViewById<View>(R.id.tinimalNavHostFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.tinimalNavHostFragment)

        val graph = navController.graph
        if (SharedPref.authToken.isNullOrBlank())
            graph.setStartDestination(R.id.authFragment)
        else
            graph.setStartDestination(R.id.mainFragment)

        navController.graph = graph

//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.mainFragment, R.id.profileFragment, R.id.favouritesFragment
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id){
                R.id.authFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.loginFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }
}