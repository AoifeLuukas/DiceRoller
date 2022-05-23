package com.example.diceroller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.diceroller.databinding.RolledDicesListFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class RolledDicesListFragment : Fragment() {

    private lateinit var viewModel: DiceRollerViewModel
    private lateinit var binding: RolledDicesListFragmentBinding
    private lateinit var adapter: DiceViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[DiceRollerViewModel::class.java]

        lifecycleScope.launchWhenStarted {
            launch {
                viewModel.rollHistoryState.collect {
                    adapter.setData(it)
                }
            }
            launch {
                viewModel.viewState.filter { it !is ViewState.History }.take(1).collectLatest {
                    findNavController()
                        .navigate(
                            RolledDicesListFragmentDirections
                                .actionRolledDicesListFragmentToDiceFragment()
                        )
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = RolledDicesListFragmentBinding.inflate(inflater, container, false).run {
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DiceViewAdapter()
        binding.rolledDiceRecyclerView.adapter = adapter
    }
}
