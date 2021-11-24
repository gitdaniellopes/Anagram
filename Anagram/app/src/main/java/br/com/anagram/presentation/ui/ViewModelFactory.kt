package br.com.anagram.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.anagram.presentation.ui.end_game.EndGameViewModel
import br.com.anagram.presentation.ui.main.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
) : ViewModelProvider.NewInstanceFactory() {
//responsáveis por instanciar ViewModels .

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel() as T
            modelClass.isAssignableFrom(EndGameViewModel::class.java) -> EndGameViewModel() as T
            else -> throw IllegalArgumentException("ViewModel Class not found")
        }
    }
}