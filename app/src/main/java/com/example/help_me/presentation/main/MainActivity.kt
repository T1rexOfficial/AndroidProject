package com.example.help_me.presentation.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.help_me.R
import com.example.help_me.presentation.FriendsFragment
import com.example.help_me.presentation.home.HomeFragment
import com.example.help_me.presentation.notifications.NotificationsFragment
import com.example.help_me.presentation.profile.ProfileFragment
import com.example.help_me.presentation.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView

    companion object TAGS {
        const val HOME_FRAGMENT = "HOME_FRAGMENT"
        const val PROFILE_FRAGMENT = "PROFILE_FRAGMENT"
     //   const val NOTIFICATIONS_FRAGMENT = "NOTIFICATIONS_FRAGMENT"
    //    const val FRIENDS_FRAGMENT = "FRIENDS_FRAGMENT"
        const val SETTINGS_FRAGMENT = "SETTINGS_FRAGMENT"
    }

    private var selectedFragment = 0

    private var listFragment: List<Fragment> =
        listOf(HomeFragment(), ProfileFragment(), SettingsFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //    val navView: BottomNavigationView = findViewById(R.id.nav_view)
   //     textMessage = findViewById(R.id.message)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
            .add(R.id.container, listFragment[0], HOME_FRAGMENT)
            .add(R.id.container, listFragment[1], PROFILE_FRAGMENT)
           // .add(R.id.container, listFragment[2], NOTIFICATIONS_FRAGMENT)
          //  .add(R.id.container, listFragment[3], FRIENDS_FRAGMENT)
            .add(R.id.container, listFragment[2], SETTINGS_FRAGMENT)
            .show(listFragment[0])
            .hide(listFragment[1])
            .hide(listFragment[2])
            //.hide(listFragment[3])

            .commit()

            selectedFragment = 0
        }

        nav_view.setOnNavigationItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.navigation_home -> {
                    if (selectedFragment != 0) {
                        transaction.show(listFragment[0]).hide(listFragment[selectedFragment])
                        selectedFragment = 0
                    }

                }
                R.id.navigation_profile -> {
                    if (selectedFragment != 1) {
                        transaction.show(listFragment[1]).hide(listFragment[selectedFragment])
                        selectedFragment = 1
                    }
                }
//                R.id.navigation_notifications -> {
//                    if (selectedFragment != 2) {
//                        transaction.show(listFragment[2]).hide(listFragment[selectedFragment])
//                        selectedFragment = 2
//                    }
//                }

                R.id.navigation_settings-> {
                    if (selectedFragment != 2) {
                        transaction.show(listFragment[2]).hide(listFragment[selectedFragment])
                        selectedFragment = 2
                    }
                }
            }
            transaction.commit()
            true
        }
    }
}
