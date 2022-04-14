package com.example.diceroller

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.diceroller.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    lateinit var updateFrag: UpdateFrag

    fun fragmentUpdate(updateFrag: UpdateFrag) {
        this.updateFrag = updateFrag
    }

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

            R.id.D4DiceMenuItem -> {
                updateFrag.updateFrag(DiceType.D4)
            }

            R.id.D6DiceMenuItem -> {
                updateFrag.updateFrag(DiceType.D6)
            }

            R.id.D8DiceMenuItem -> {
                updateFrag.updateFrag(DiceType.D8)
            }

            R.id.D10DiceMenuItem -> {
                updateFrag.updateFrag(DiceType.D10)
            }

            R.id.D12DiceMenuItem -> {
                updateFrag.updateFrag(DiceType.D12)
            }

            R.id.D20DiceMenuItem -> {
                updateFrag.updateFrag(DiceType.D20)
            }

            else -> {
                binding.fragmentContainerView.getFragment<DiceFragment>().diceType.value = DiceType.D6
            }
        }
        checkSelectedItem(selectedItem)
        navigationMenu.visibility = View.GONE

        return true
    }

    private fun changeDice(frag: Fragment) {
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