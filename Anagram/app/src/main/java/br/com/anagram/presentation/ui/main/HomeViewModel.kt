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

    val hit = MutableLiveData<Boolean?>(null)

    init {
        _score.value = 0
        _round.value = 1
    }

    fun createSecret() {
        val secretWord = sortWord(carManufacturerList)
        _secret.value = secretWord
        scrambleWord(secretWord)
    }

    private fun sortWord(list: List<String>): String {
        val idx = Random.nextInt(list.indices)
        return list[idx]
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

    fun send(hint: String) {
        if (hint.equals(secret.value, ignoreCase = true)){
            hit.value = true
            var currentScore: Int = score.value ?: 0
            currentScore++
            _score.value = currentScore
        } else {
            hit.value = false
        }
    }

    fun goRound(){
        var round = _round.value ?: 0
        round++
        _round.value = round
    }

    fun newGame() {
        _round.value = 1
        _score.value = 0
    }


    private val carManufacturerList = arrayListOf(
        "Adamo",
        "Agrale",
        "Aldee",
        "Alfa Romeo",
        "Americar",
        "Avallone",
        "Audi",
        "Aurora",
        "Bianco",
        "BMW",
        "Bola",
        "Brasinca",
        "CBP",
        "CBT",
        "Chamonix",
        "Chery",
        "Chevrolet",
        "Chrysler Dodge",
        "Citroën",
        "Concorde",
        "Corona",
        "Cross Lander",
        "Daewoo",
        "Daihatsu",
        "Dankar",
        "DKW -Vemag",
        "Edra",
        "Emis",
        "Engerauto",
        "Engesa",
        "Envemo",
        "Envesa",
        "Effa",
        "Farus",
        "Fiat",
        "FNM",
        "Ford",
        "Furglass",
        "Geely",
        "Glaspac",
        "GMC",
        "Grancar",
        "Gurgel",
        "Hofstetter",
        "Honda",
        "Hummer",
        "Hyundai",
        "IBAP",
        "Inbrave",
        "Infiniti",
        "Ita",
        "JAC",
        "Jaguar",
        "Jeep",
        "JPX",
        "Kadron",
        "Kia",
        "Land Rover",
        "Lexus",
        "Lifan",
        "Lincoln",
        "Macan",
        "Mahindra",
        "Malzoni",
        "Matra Veículos",
        "Mazda",
        "Megastar Veículos",
        "Mercedes Benz",
        "Mirage",
        "Mitsubishi",
        "Miura",
        "Monarca",
        "NBM",
        "Nissan",
        "Nobre",
        "PAG / Dacon",
        "Peugeot",
        "Polystilo",
        "Puma",
        "Py Motors",
        "Ragge",
        "Renault",
        "Romi",
        "Saab",
        "Simca",
        "Santa Matilde",
        "San Vito",
        "SEAT",
        "STV",
        "Spiller Mattei",
        "Subaru",
        "Suzuki",
        "TAC Motors",
        "Tanger",
        "Toyota",
        "Trimax",
        "Troller",
        "Villa",
        "Volvo",
        "Volkswagen",
        "WMV"

    )
}