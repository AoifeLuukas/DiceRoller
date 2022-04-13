package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import com.example.diceroller.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        setListeners()
    }

    private fun setListeners() {
        val navigationMenu = binding.navView

        val menuButton = binding.menuButton

        toggleNavigation(menuButton, navigationMenu)

        navigationMenu.setNavigationItemSelectedListener(this)
    }


    private fun toggleNavigation(menuButton: View, navigationMenu: View) {
        menuButton.setOnClickListener {
            when (navigationMenu.visibility) {
                View.GONE -> {
                    navigationMenu.visibility = View.VISIBLE
                }
                View.VISIBLE -> {
                    navigationMenu.visibility = View.GONE
                }
                else -> {
                    navigationMenu.visibility = View.GONE
                }
            }
        }
    }

    override fun onNavigationItemSelected(selectedItem: MenuItem): Boolean {
        val navigationMenu = binding.navView
        when(selectedItem.itemId) {
            R.id.D20DiceMenuItem -> {
                changeDice(D20DiceFragment())
            }

            R.id.D6DiceMenuItem -> {
                changeDice(D6DiceFragment())
            }

            else -> {
                changeDice(D6DiceFragment())
            }
        }
        checkSelectedItem(selectedItem)
        navigationMenu.visibility = View.GONE

        return true
    }

    private fun changeDice(frag: Fragment) {
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(binding.fragmentContainerView.id, frag).commit()

    }

    private fun checkSelectedItem(selectedItem: MenuItem) {
        val navigationMenu = binding.navView
        for (item in navigationMenu.menu) {
            item.isChecked = false
        }
        selectedItem.isChecked = true

        binding.titleText.text = selectedItem.title
    }
}