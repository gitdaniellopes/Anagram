package br.com.anagram.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.anagram.R
import br.com.anagram.databinding.FragmentHomeBinding
import br.com.anagram.presentation.ui.ViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory()
        homeViewModel = ViewModelProvider(viewModelStore, factory)[HomeViewModel::class.java]

        homeViewModel.createSecret()

        homeViewModel.challenge.observe(viewLifecycleOwner) { challenge ->
            binding.tvRandomLetters.text = challenge
        }

        binding.buttonSend.setOnClickListener {
            if (homeViewModel.round.value == 10) {
                Toast.makeText(context, "Jogo encerrado", Toast.LENGTH_SHORT).show()
                binding.buttonSend.text = getText(R.string.end_game)
                endGame()
            }
            if (homeViewModel.round.value!! <= 10) {
                val hit = binding.edHint.text.toString().uppercase()
                homeViewModel.send(hit)
                homeViewModel.goRound()
                homeViewModel.createSecret()
            }
        }

        homeViewModel.score.observe(viewLifecycleOwner) {
            binding.tvPunctuation.text = it.toString()
        }

        homeViewModel.hit.observe(viewLifecycleOwner, { isHit ->
            when (isHit) {
                true -> {
                    Toast.makeText(context, "Certa resposta!", Toast.LENGTH_SHORT).show()
                }
                false -> {
                    Toast.makeText(
                        context,
                        "Resposta errada =/ -  a resposta correta é: ${homeViewModel.secret.value}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun endGame() {
        val action = HomeFragmentDirections.actionFragmentHomeToEndGameFragment()
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}