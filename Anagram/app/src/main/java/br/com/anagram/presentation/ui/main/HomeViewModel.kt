package br.com.anagram.presentation.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random
import kotlin.random.nextInt

class HomeViewModel : ViewModel() {

    private val _secret = MutableLiveData<String>()
    val secret = _secret

    private val _challenge = MutableLiveData<String>()
    val challenge = _challenge

    private val _score = MutableLiveData<Int>()
    val score = _score

    private val _round = MutableLiveData<Int>()
    val round = _round

    private val _goToEndGame = MutableLiveData<Boolean?>()
    val goToEndGame = _goToEndGame

    val hint = MutableLiveData<String>()

    val hit = MutableLiveData<Boolean?>(null)

    val secretOld = MutableLiveData("")

    init {
        _score.value = 0
        _round.value = 1
    }

    fun createSecret() {
        val secretWord = sortWord(cars)
        _secret.value = secretWord
        scrambleWord(secretWord)
    }

    private fun scrambleWord(word: String) {
        val letters = arrayListOf<Char>()
        word.forEach {
            letters.add(it)
        }
        /** Embaralha aleatoriamente os elementos nesta lista mutável. */
        letters.shuffle()
        var scrambleLetter = ""
        for (l in letters) {
            scrambleLetter += l
        }
        _challenge.value = scrambleLetter.uppercase()
    }

    private fun sortWord(list: List<String>): String {
        val idx = Random.nextInt(list.indices)
        return list[idx]
    }

    private fun send(hint: String) {
        if (hint.equals(secret.value, ignoreCase = true)) {
            hit.value = true
            var currentScore: Int = score.value ?: 0
            currentScore++
            _score.value = currentScore
        } else {
            hit.value = false
        }
        secretOld.value = secret.value
        clearHint()
    }

    private fun clearHint() {
        hint.value = ""
    }

    private fun goRound() {
        var round = _round.value ?: 0
        round++
        _round.value = round
    }

    private fun endGame() {
        _goToEndGame.value = true
    }

    fun sailedEndGame() {
        _goToEndGame.value = null
    }

    fun sendHint() {
        if (round.value == 10) {
            endGame()
        }
        if (round.value!! <= 10) {
            send(hint.value.toString())
            goRound()
            createSecret()
        }
    }

    fun newGame() {
        _round.value = 1
        _score.value = 0
    }

    private val cars = arrayListOf(
        "Sandero",
        "Corola",
        "Onix",
        "Agile",
        "Focus",
        "Maverik",
        "Peugeot",
        "Versa",
        "Etios",
        "Fluence",
        "Bravo",
        "Golf",
        "Opala",
        "Civic",
        "Hilux",
        "Amaroc",
        "Fiesta",
        "Fiorino",
        "Gol",
        "Saveiro",
        "Ranger",
        "Palio",
        "Siena",
        "Celta",
        "Voyage",
        "HB20",
        "Corsa",
        "Cobalt",
        "Punto",
        "Cruze",
        "Logan",
        "Uno"

    )
}