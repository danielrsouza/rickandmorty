package com.example.ryckandmorty.ui.episodes

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
import com.example.ryckandmorty.databinding.FragmentEpisodeBinding
import com.example.ryckandmorty.ui.episodes.adapters.EpisodeAdapter
import com.example.ryckandmorty.ui.viewmodels.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>() {
    override val viewModel: EpisodeViewModel by viewModels()

    @Inject
    lateinit var episodeAdapter: EpisodeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEpisodes()
        observerStateFlow()
        initObserver()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerEpisode.apply {
            adapter = episodeAdapter
            layoutManager = LinearLayoutManager(context)
        }
        binding.recyclerEpisode.addItemDecoration(
            DividerItemDecoration(
                binding.root.context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.getEpisodes().collectLatest {
                episodeAdapter.submitData(it)
            }
        }
    }

    private fun observerStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            episodeAdapter.loadStateFlow.collectLatest { loadStates ->

                when (loadStates.refresh) {
                    is LoadState.Loading -> binding.progressBarEpisode.visibility = View.VISIBLE
                    is LoadState.NotLoading -> binding.progressBarEpisode.visibility = View.GONE
                    is LoadState.Error -> {
                        binding.progressBarEpisode.visibility = View.GONE
                        Toast.makeText(context, "Ocorreu um erro", Toast.LENGTH_LONG).show()
                    }
                    else -> Log.d("observerStateFlow", "problem with observerStateFlow... ")
                }
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEpisodeBinding = FragmentEpisodeBinding.inflate(inflater, container, false)

}