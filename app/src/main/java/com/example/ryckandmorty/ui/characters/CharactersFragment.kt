package com.example.ryckandmorty.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ryckandmorty.databinding.FragmentCharactersBinding
import com.example.ryckandmorty.ui.base.BaseFragment

class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {
    override val viewModel: CharactersViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCharactersBinding = FragmentCharactersBinding.inflate(inflater, container, false)

}