package br.com.anagram.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.anagram.databinding.FragmentHomeBinding
import br.com.anagram.presentation.ui.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val dataBinding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = dataBinding.root

        val factory = ViewModelFactory()
        homeViewModel = ViewModelProvider(viewModelStore, factory)[HomeViewModel::class.java]

        dataBinding.homeViewModel = homeViewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.createSecret()

        homeViewModel.goToEndGame.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    goToEndGame()
                }
                homeViewModel.sailedEndGame()
            }
        }
    }

    private fun goToEndGame() {
        val action = HomeFragmentDirections.actionFragmentHomeToEndGameFragment()
        findNavController().navigate(action)
    }
}