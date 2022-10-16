package ru.cordyapp.tinimal.presentation

import android.Manifest
import android.content.pm.PackageManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), REQUEST_CODE_PERMISSION
                )
            }
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.tinimalNavHostFragment)

        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id){
                R.id.mainFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.profileFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.favouritesFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                else -> {
                    bottomNavigationView.visibility = View.GONE
                }
            }
        }
    }
    companion object {
        private const val REQUEST_CODE_PERMISSION = 1
    }
}