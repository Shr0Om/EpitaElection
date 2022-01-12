package com.epita.epitaelection.View.Game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.epita.epitaelection.Model.Joueur
import com.epita.epitaelection.R
import com.epita.epitaelection.databinding.GameFragmentBinding
import kotlin.math.floor


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment(R.layout.game_fragment) {

    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!
    private var tour = 0
    private var selectedPlayer = "Macron"
    private val playerAvailable = arrayOf("Zemour", "Macron", "Marine", "Melenchon")
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

        val boutonn = view.findViewById(R.id.game_main_button) as Button
        boutonn.setOnClickListener {
            boutonn.isEnabled = false
            boutonn.text = "Relancer"
            startGame()
            //gameLife()
            //gameEnd()

        }
    }

    private fun gameEnd() {
        val boutonn: Button = view?.findViewById(R.id.game_main_button) as Button
        boutonn.setOnClickListener {
        }
    }

    private fun gameLife() {
        while (true) {
            tour++
        }
    }

    private fun botInit(){
        var id_Bot = 0
        for ( i in playerAvailable.indices) {
            if (playerAvailable[i] != selectedPlayer) {
                playerBot[id_Bot] = Joueur(playerAvailable[i])
                id_Bot++
            }else {
                mainPlayer = Joueur(playerAvailable[i])
            }
        }
    }

    private fun boardInit(){
        // sale a refactor
        binding.player3PlayerName.text = playerBot[0].name
        binding.player1PlayerPv.text = """PV: ${playerBot[0].pv}"""
        binding.player1PlayerPf.text = """Vote: ${playerBot[0].vote}"""
        binding.pionJoueur1.setImageResource(R.drawable.pion_zemour)

        binding.player2PlayerName.text = playerBot[1].name
        binding.player2PlayerPv.text = """PV: ${playerBot[1].pv}"""
        binding.player2PlayerPf.text = """Vote: ${playerBot[1].vote}"""
        binding.pionJoueur2.setImageResource(R.drawable.pion_lepen)


        binding.player3PlayerName.text = playerBot[2].name
        binding.player3PlayerPv.text = """PV: ${playerBot[2].pv}"""
        binding.player3PlayerPf.text = """Vote: ${playerBot[2].vote}"""
        binding.pionJoueur3.setImageResource(R.drawable.pion_melenchon)


        binding.mainPlayerName.text = mainPlayer.name
        binding.mainPlayerPv.text = """PV: ${mainPlayer.pv}"""
        binding.mainPlayerPf.text = """Vote: ${mainPlayer.vote}"""
        binding.pionJoueurMain.setImageResource(R.drawable.pion_macron)


    }

    private fun startGame() {
        botInit()
        boardInit()
        val gameBar = view?.findViewById(R.id.game_status) as TextView
        gameBar.text = "${Starter(MyRandom(4))} commence"
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
        getMyDe()
    }

    private fun MyRandom(max: Int): Int {
        return floor(Math.random() * (max - 1 + 1) + 1).toInt()
    }

    private fun Starter(number: Int): String? {
        tour = number;
        when (number) {
            1 -> return "TikTok Masta"
            2 -> return "Le Z"
            3 -> return "MACRON"
            4 -> return "LE PEN"
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