package br.com.anagram.presentation.ui.end_game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.anagram.databinding.EndGameFragmentBinding
import br.com.anagram.presentation.ui.ViewModelFactory
import br.com.anagram.presentation.ui.main.HomeViewModel

class EndGameFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private val binding: EndGameFragmentBinding by lazy {
        EndGameFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory = ViewModelFactory()
        homeViewModel = ViewModelProvider(viewModelStore, factory)[HomeViewModel::class.java]

        binding.homeViewModel = HomeViewModel()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newGame.setOnClickListener {
            val action = EndGameFragmentDirections.actionEndGameFragmentToFragmentHome()
            findNavController().navigate(action)
            homeViewModel.newGame()
        }
    }
}