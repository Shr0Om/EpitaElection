package com.epita.epitaelection.View.Game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.epita.epitaelection.Model.Joueur
import com.epita.epitaelection.R
import com.epita.epitaelection.databinding.GameFragmentBinding
import kotlin.math.floor
import kotlin.random.Random


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment(R.layout.game_fragment) {

    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!
    private var tour = 0
    private var selectedPlayer = "Macron"
    private val playerAvailable = arrayOf("Zemour", "Macron", "Marine", "Melenchon")
    private val playerImage = arrayOf(R.drawable.pion_zemour, R.drawable.pion_macron, R.drawable.pion_lepen, R.drawable.pion_melenchon)
    private val playerBot = arrayOf(Joueur(""),Joueur(""),Joueur(""))
    private var mainPlayer = Joueur("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gameBoard.setImageResource(R.drawable.paris_board)
        binding.ralancerButton.isEnabled = false

        init()
        val boutonn = view.findViewById(R.id.game_main_button) as Button
        boutonn.setOnClickListener {
            boutonn.isEnabled = false
            boutonn.text = "Relancer"
            startGame()
        }
    }

    private fun init(){
        botInit()
        boardInit()
    }
    private fun startGame() {
        val gameBar = view?.findViewById(R.id.game_status) as TextView
        gameBar.text = "${Starter(MyRandom(4))} commence"
        gameTour()
    }

    private fun gameTour() {
        while (tour != 4) {
            if (tour % 4 == 3){
                PlayerTurn()
            }
            else{
                BotTurn(tour % 4)
            }
            println(tour)
            tour++
        }
        tour = 0
    }

    private fun gameEnd() {
        val boutonn: Button = view?.findViewById(R.id.game_main_button) as Button
        boutonn.setOnClickListener {
        }
    }

    private fun botInit(){
        var id_Bot = 0
        for ( i in playerAvailable.indices) {

            if (playerAvailable[i] != selectedPlayer) {
                playerBot[id_Bot] = Joueur(playerAvailable[i])
                playerBot[id_Bot].imageSrc = playerImage[i]
                id_Bot++
            }else {
                mainPlayer = Joueur(playerAvailable[i])
                mainPlayer.imageSrc = playerImage[i]
            }

        }
    }

    private fun boardInit(){
        // sale a refactor
        binding.player1PlayerName.text = playerBot[0].name
        binding.player1PlayerPv.text = """PV: ${playerBot[0].pv}"""
        binding.player1PlayerPf.text = """Vote: ${playerBot[0].vote}"""
        binding.pionJoueur1.setImageResource(playerBot[0].imageSrc)

        binding.player2PlayerName.text = playerBot[1].name
        binding.player2PlayerPv.text = """PV: ${playerBot[1].pv}"""
        binding.player2PlayerPf.text = """Vote: ${playerBot[1].vote}"""
        binding.pionJoueur2.setImageResource(playerBot[1].imageSrc)

        binding.player3PlayerName.text = playerBot[2].name
        binding.player3PlayerPv.text =  "PV: " + playerBot[2].pv
        binding.player3PlayerPf.text = "Vote: " + playerBot[2].vote
        binding.pionJoueur3.setImageResource(playerBot[2].imageSrc)


        binding.mainPlayerName.text = mainPlayer.name
        binding.mainPlayerPv.text = "PV: " + mainPlayer.pv
        binding.mainPlayerPf.text = "Vote: " + mainPlayer.vote
        binding.pionJoueurMain.setImageResource(mainPlayer.imageSrc)
    }



    private fun getMyDe() {
        val De1 = requireView().findViewById<View>(R.id.game_de_1) as ToggleButton
        val De2 = requireView().findViewById<View>(R.id.game_de_2) as ToggleButton
        val De3 = requireView().findViewById<View>(R.id.game_de_3) as ToggleButton
        val De4 = requireView().findViewById<View>(R.id.game_de_4) as ToggleButton
        val De5 = requireView().findViewById<View>(R.id.game_de_5) as ToggleButton
        val De6 = requireView().findViewById<View>(R.id.game_de_6) as ToggleButton

        De1.text = MyRandom(6).toString()
        De2.text = MyRandom(6).toString()
        De3.text = MyRandom(6).toString()
        De4.text = MyRandom(6).toString()
        De5.text = MyRandom(6).toString()
        De6.text = MyRandom(6).toString()
    }


    private fun PlayerTurn() {
        var relauch = 0
        getMyDe()
        println("Player turn")

        binding.ralancerButton.isEnabled = true
        binding.achatButton.isEnabled = true
        binding.gameMainButton.isEnabled = true

        binding.gameStatus.text = "A toi de jouer"

        binding.gameMainButton.setOnClickListener{
            gameTour()
        }
        binding.TakeParisButton.setOnClickListener{
            takeParis(mainPlayer)
        }
        binding.buttonBackHome.setOnClickListener{
            leaveParis(mainPlayer)
        }
        binding.ralancerButton.setOnClickListener{
            if (relauch < 3){
                getMyDe()
                relauch++
            } else {
                binding.gameStatus.text = "Il ne vous restes plus de relance"
            }
        }
    }

    private fun leaveParis(joueur: Joueur){
        binding.pionJoueurMain.isClickable = false
        binding.TakeParisButton.isClickable = true

        binding.pionPresident.isVisible = false
        binding.pionJoueurMain.setImageResource(joueur.imageSrc)

    }

    private fun takeParis(joueur: Joueur){
        binding.pionPresident.isVisible = true
        binding.TakeParisButton.isClickable = false
        binding.pionJoueurMain.isClickable = true

            binding.pionPresident.setImageResource(joueur.imageSrc)
        binding.pionJoueurMain.setImageResource(R.drawable.away)
    }

    private fun BotTurn(id: Int){
        binding.gameStatus.text = playerBot[id].name + " joue"
        getMyDe()
        println("sleep")
        Thread.sleep(2000)
        println("stop sleep")
    }

    private fun MyRandom(max: Int): Int {
        return Random.nextInt(0, 4)
    }

    private fun Starter(number: Int): String? {
        tour = number
        when (number) {
            0 -> return "TikTok Masta"
            1 -> return "Le Z"
            2 -> return "MACRON"
            3 -> return "LE PEN"
        }
        return "no Data"
    }

    fun rotationStart() {
        val rotation = MyRandom(4)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}