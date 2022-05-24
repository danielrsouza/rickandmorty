package com.example.ryckandmorty.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ryckandmorty.base.BaseFragment
import com.example.ryckandmorty.databinding.FragmentCharactersBinding
import com.example.ryckandmorty.ui.characters.adapters.CharactersAdapter
import com.example.ryckandmorty.ui.viewmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {
    override val viewModel: CharactersViewModel by viewModels()

    @Inject
    lateinit var characterAdapter: CharactersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCharacters()
        observerStateFlow()
        initObserver()
        setupRecyclerView()
    }

    // Handle with state for att the UI
    private fun observerStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            characterAdapter.loadStateFlow.collectLatest { loadStates ->

                when (loadStates.refresh) {
                    is LoadState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is LoadState.NotLoading -> binding.progressBar.visibility = View.GONE
                    is LoadState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Ocorreu um erro", Toast.LENGTH_LONG).show()
                    }
                    else -> Log.d("observerStateFlow", "problem with observerStateFlow... ")
                }
            }
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.fetchCharacters().collectLatest {
                characterAdapter.submitData(it)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerCharacters.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(context)
        }
        binding.recyclerCharacters.addItemDecoration(
            DividerItemDecoration(
                binding.root.context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCharactersBinding = FragmentCharactersBinding.inflate(inflater, container, false)

}