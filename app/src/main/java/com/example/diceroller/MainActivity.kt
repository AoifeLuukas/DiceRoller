package com.example.diceroller

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.diceroller.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RolledDiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(RolledDiceViewModel::class.java)

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
            R.id.D4DiceMenuItem -> {
                viewModel.triggerD4()
            }

            R.id.D6DiceMenuItem -> {
                viewModel.triggerD6()
            }

            R.id.D8DiceMenuItem -> {
                viewModel.triggerD8()
            }

            R.id.D10DiceMenuItem -> {
                viewModel.triggerD10()
            }

            R.id.D12DiceMenuItem -> {
                viewModel.triggerD12()
            }

            R.id.D20DiceMenuItem -> {
                viewModel.triggerD20()
            }

            else -> {
                viewModel.triggerD6()
            }

        }

        checkSelectedItem(selectedItem)
        navigationMenu.visibility = View.GONE

        return true
    }

    private fun changeFragement(frag: Fragment) {
        findNavController(binding.fragmentContainerView.id).navigate(frag.id)
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