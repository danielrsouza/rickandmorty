package com.example.ryckandmorty.ui.planets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ryckandmorty.base.BaseFragment
import com.example.ryckandmorty.databinding.FragmentPlanetsBinding

class PlanetsFragment : BaseFragment<FragmentPlanetsBinding, PlanetsViewModel>() {
    override val viewModel: PlanetsViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlanetsBinding = FragmentPlanetsBinding.inflate(inflater, container, false)

}