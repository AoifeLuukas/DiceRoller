package com.example.diceroller

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.diceroller.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DiceRollerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this)[DiceRollerViewModel::class.java]

        setListeners()
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                if (it is ViewState.RollDie) {
                    binding.title.text =
                        getString(R.string.toolbar_label, it.diceType)
                }
            }
        }
    }

    private fun setListeners() {
        binding.menuButton.setOnClickListener { toggleNavMenu() }
        binding.navView.setNavigationItemSelectedListener(this)
    }

    private fun toggleNavMenu() {
        with(binding) {
            when (navView.visibility) {
                View.GONE -> navView.visibility = View.VISIBLE
                View.VISIBLE -> navView.visibility = View.GONE
                else -> navView.visibility = View.GONE
            }
        }
    }

    override fun onNavigationItemSelected(selectedItem: MenuItem): Boolean {

        fun checkSelectedItem(selectedItem: MenuItem) {
            val navigationMenu = binding.navView

            navigationMenu.menu.forEach {
                it.isChecked = false
            }
            selectedItem.isChecked = true
        }

        when (selectedItem.itemId) {
            R.id.D4DiceMenuItem -> viewModel.onChangeDieIntent(DiceType.D4)
            R.id.D6DiceMenuItem -> viewModel.onChangeDieIntent(DiceType.D6)
            R.id.D8DiceMenuItem -> viewModel.onChangeDieIntent(DiceType.D8)
            R.id.D10DiceMenuItem -> viewModel.onChangeDieIntent(DiceType.D10)
            R.id.D12DiceMenuItem -> viewModel.onChangeDieIntent(DiceType.D12)
            R.id.D20DiceMenuItem -> viewModel.onChangeDieIntent(DiceType.D20)
            R.id.LastRollsMenuItem -> viewModel.onViewRollHistoryIntent()
            else -> throw Exception("Unknown Navigation Item Selected")
        }

        checkSelectedItem(selectedItem)
        toggleNavMenu()
        return true
    }
}
