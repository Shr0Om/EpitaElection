package com.epita.epitaelection.View.Game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.epita.epitaelection.Model.Joueur
import com.epita.epitaelection.Model.Main
import com.epita.epitaelection.R
import com.epita.epitaelection.databinding.GameFragmentBinding
import kotlin.random.Random


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment(R.layout.game_fragment) {
    val args: GameFragmentArgs by navArgs()

    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!
    private var tour = 0
    private var selectedPlayer = ""
    private val playerAvailable = arrayOf("Zemour", "Macron", "Marine", "Melenchon")
    private val playerImage = arrayOf(
        R.drawable.pion_zemour,
        R.drawable.pion_macron,
        R.drawable.pion_lepen,
        R.drawable.pion_melenchon
    )
    private val playerBot = arrayOf(Joueur(""), Joueur(""), Joueur(""))
    private var mainPlayer = Joueur("")
    private var handMainPlayer = Main()

    private var PlayerInTheMiddle = Joueur("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedPlayer = args.character
        binding.gameBoard.setImageResource(R.drawable.paris_board)
        binding.ralancerButton.isEnabled = false
        init()
        val boutonn = view.findViewById(R.id.game_main_button) as Button
        boutonn.setOnClickListener {
            boutonn.isEnabled = false
            boutonn.text = "Passer"
            startGame()
        }
    }

    private fun gainPoint() {
        if (PlayerInTheMiddle != Joueur("")) {
            if (PlayerInTheMiddle.name == playerBot[0].name) {
                playerBot[0].vote = playerBot[0].vote + 10
            }
            if (PlayerInTheMiddle.name == playerBot[1].name) {
                playerBot[1].vote = playerBot[1].vote + 10
            }
            if (PlayerInTheMiddle.name == playerBot[2].name) {
                playerBot[2].vote = playerBot[2].vote + 10
            }
            if (PlayerInTheMiddle.name == mainPlayer.name) {
                mainPlayer.vote = mainPlayer.vote + 10
            }
        }
        boardReload()
    }

    private fun init() {
        botInit()
        boardInit()
    }

    private fun startGame() {
        val gameBar = view?.findViewById(R.id.game_status) as TextView
        gameBar.text = "${Starter(MyRandom(0, 4))} commence"
        gameTour()
    }

    private fun gameTour() {
        while (tour != 4) {
            if (tour % 4 == 3) {
                PlayerTurn()
            } else {
                BotTurn(tour % 4)
            }
            println(tour)
            tour++
        }
        tour = 0
    }

    private fun gameEnd() {
        binding.ralancerButton.isEnabled = false
        binding.gameMainButton.isEnabled = false
        binding.achatButton.isEnabled = false
        binding.TakeParisButton.isEnabled = false
        binding.buttonBackHome.isEnabled = false
    }

    private fun botInit() {
        var id_Bot = 0
        for (i in playerAvailable.indices) {

            if (playerAvailable[i] != selectedPlayer) {
                playerBot[id_Bot] = Joueur(playerAvailable[i])
                playerBot[id_Bot].imageSrc = playerImage[i]
                id_Bot++
            } else {
                mainPlayer = Joueur(playerAvailable[i])
                mainPlayer.imageSrc = playerImage[i]
            }

        }
    }

    private fun boardReload() {
        binding.player1PlayerPv.text = playerBot[0].pv.toString()
        binding.player1PlayerPf.text = playerBot[0].vote.toString()
        binding.player1PlayerMoney.text = playerBot[0].gold.toString()

        binding.player2PlayerPv.text = playerBot[1].pv.toString()
        binding.player2PlayerPf.text = playerBot[1].vote.toString()
        binding.player2PlayerMoney.text = playerBot[1].gold.toString()

        binding.player3PlayerPv.text = playerBot[2].pv.toString()
        binding.player3PlayerPf.text = playerBot[2].vote.toString()
        binding.player3PlayerMoney.text = playerBot[2].gold.toString()

        binding.mainPlayerPv.text = mainPlayer.pv.toString()
        binding.mainPlayerPf.text = mainPlayer.vote.toString()
        binding.mainPlayerMoney.text = mainPlayer.gold.toString()
    }

    private fun boardInit() {
        // sale a refactor
        binding.player1PlayerName.text = playerBot[0].name
        binding.player1PlayerPv.text = playerBot[0].pv.toString()
        binding.player1PlayerPf.text = playerBot[0].vote.toString()
        binding.player1PlayerMoney.text = playerBot[0].gold.toString()
        binding.pionJoueur1.setImageResource(playerBot[0].imageSrc)

        binding.player2PlayerName.text = playerBot[1].name
        binding.player2PlayerPv.text = playerBot[1].pv.toString()
        binding.player2PlayerPf.text = playerBot[1].vote.toString()
        binding.player2PlayerMoney.text = playerBot[1].gold.toString()
        binding.pionJoueur2.setImageResource(playerBot[1].imageSrc)

        binding.player3PlayerName.text = playerBot[2].name
        binding.player3PlayerPv.text = playerBot[2].pv.toString()
        binding.player3PlayerPf.text = playerBot[2].vote.toString()
        binding.player3PlayerMoney.text = playerBot[2].gold.toString()
        binding.pionJoueur3.setImageResource(playerBot[2].imageSrc)


        binding.mainPlayerName.text = mainPlayer.name
        binding.mainPlayerPv.text = mainPlayer.pv.toString()
        binding.mainPlayerPf.text = mainPlayer.vote.toString()
        binding.mainPlayerMoney.text = mainPlayer.gold.toString()
        binding.pionJoueurMain.setImageResource(mainPlayer.imageSrc)
    }


    private fun getDePicture(id: Int): Int {
        when (id) {
            1 -> return R.drawable.de1
            2 -> return R.drawable.de2
            3 -> return R.drawable.de3
            4 -> return R.drawable.de4
            5 -> return R.drawable.de5
            6 -> return R.drawable.de6
        }
        return R.drawable.de1
    }

    private fun getMyDe() {
        binding.gameDe1.setImageResource(getDePicture(MyRandom(1, 7)))
        binding.gameDe2.setImageResource(getDePicture(MyRandom(1, 7)))
        binding.gameDe3.setImageResource(getDePicture(MyRandom(1, 7)))
        binding.gameDe4.setImageResource(getDePicture(MyRandom(1, 7)))
        binding.gameDe5.setImageResource(getDePicture(MyRandom(1, 7)))
        binding.gameDe6.setImageResource(getDePicture(MyRandom(1, 7)))
    }


    private fun CheckIfWin() {
        gainPoint()
        if (playerBot[0].vote >= 60) {
            gameEnd()
            binding.gameStatus.text = playerBot[0].name + " à gagné"
        } else if (playerBot[1].vote >= 60) {
            gameEnd()
            binding.gameStatus.text = playerBot[1].name + " à gagné"
        } else if (playerBot[2].vote >= 60) {
            gameEnd()
            binding.gameStatus.text = playerBot[2].name + " à gagné"
        } else if (mainPlayer.vote >= 60) {
            gameEnd()
            binding.gameStatus.text = mainPlayer.name + " à gagné"
        } else {
            gameTour()
        }

    }

    private fun achat(joueur: Joueur) {
        if (joueur.gold >= 20) {
            if (handMainPlayer.card1 == 0) {
                giveCard(binding.gameCard1)
                joueur.gold = joueur.gold - 20
                handMainPlayer.card1 = 1
                boardReload()
            } else if (handMainPlayer.card2 == 0) {
                giveCard(binding.gameCard2)
                joueur.gold = joueur.gold - 20
                handMainPlayer.card2 = 1
                boardReload()
            } else if (handMainPlayer.card3 == 0) {
                giveCard(binding.gameCard3)
                joueur.gold = joueur.gold - 20
                handMainPlayer.card3 = 1
                boardReload()
            } else if (handMainPlayer.card4 == 0) {
                giveCard(binding.gameCard4)
                joueur.gold = joueur.gold - 20
                handMainPlayer.card4 = 1
                boardReload()
            } else if (handMainPlayer.card5 == 0) {
                giveCard(binding.gameCard5)
                joueur.gold = joueur.gold - 20
                handMainPlayer.card5 = 1
                boardReload()
            } else if (handMainPlayer.card6 == 0) {
                giveCard(binding.gameCard6)
                joueur.gold = joueur.gold - 20
                handMainPlayer.card6 = 1
                boardReload()
            } else{
                binding.gameStatus.text = "Trop de carte"
            }
        }
        else {
            binding.gameStatus.text = "Trop pauvre il vous faut 20 gold"
        }
    }

    private fun getRandomCard(): Int {
        when (MyRandom(1, 11)) {
            1 -> return R.drawable.card1
            2 -> return R.drawable.card2
            3 -> return R.drawable.card3
            4 -> return R.drawable.card4
            5 -> return R.drawable.card5
            6 -> return R.drawable.card6
            7 -> return R.drawable.card7
            8 -> return R.drawable.card8
            9 -> return R.drawable.card9
            10 -> return R.drawable.card10
        }
        return R.drawable.card10
    }

    private fun giveCard(image: ImageView) {
        image.setImageResource(getRandomCard())
    }

    private fun PlayerTurn() {
        var relauch = 0
        getMyDe()
        initListenerOnDe()
        initListenerOnDe()
        println("Player turn")

        binding.achatButton.isEnabled = true
        binding.gameMainButton.isEnabled = true

        binding.gameStatus.text = "A toi de jouer"

        binding.gameMainButton.setOnClickListener {
            CheckIfWin()
        }
        binding.TakeParisButton.setOnClickListener {
            takeParis(mainPlayer)
        }
        binding.achatButton.setOnClickListener {
            achat(mainPlayer)
        }
        binding.buttonBackHome.setOnClickListener {
            leaveParis(mainPlayer)
        }
        binding.ralancerButton.setOnClickListener {
            if (relauch < 3) {
                reGetMyDe()
                relauch++
            } else {
                binding.gameStatus.text = "Il ne vous restes plus de relance"
            }
        }
    }
    private fun initListenerOnDe(){
        binding.gameDe1.setOnClickListener{rotateDeColor(binding.gameDe1)}
        binding.gameDe2.setOnClickListener{rotateDeColor(binding.gameDe2)}
        binding.gameDe3.setOnClickListener{rotateDeColor(binding.gameDe3)}
        binding.gameDe4.setOnClickListener{rotateDeColor(binding.gameDe4)}
        binding.gameDe5.setOnClickListener{rotateDeColor(binding.gameDe5)}
        binding.gameDe6.setOnClickListener{rotateDeColor(binding.gameDe6)}
    }


    private fun rotateDeColor(imageView: ImageView){
            if (imageView.contentDescription == "" || imageView.contentDescription == null){
                imageView.setBackgroundColor(0xFF00FF00.toInt());
                imageView.contentDescription = "pressed"
                binding.ralancerButton.isEnabled = true
            }else{
                imageView.setBackgroundColor(0xFFFFFF);
                imageView.contentDescription = "";
            }
        }


    private fun reGetMyDe(){
        if (binding.gameDe1.contentDescription == "pressed"){
            binding.gameDe1.setImageResource(getDePicture(MyRandom(1, 7)))
            rotateDeColor(binding.gameDe1)
        }
        if (binding.gameDe2.contentDescription == "pressed"){
            binding.gameDe2.setImageResource(getDePicture(MyRandom(1, 7)))
            rotateDeColor(binding.gameDe2)
        }
        if (binding.gameDe3.contentDescription == "pressed"){
            binding.gameDe3.setImageResource(getDePicture(MyRandom(1, 7)))
            rotateDeColor(binding.gameDe3)
        }
        if (binding.gameDe4.contentDescription == "pressed"){
            binding.gameDe4.setImageResource(getDePicture(MyRandom(1, 7)))
            rotateDeColor(binding.gameDe4)
        }
        if (binding.gameDe5.contentDescription == "pressed"){
            binding.gameDe5.setImageResource(getDePicture(MyRandom(1, 7)))
            rotateDeColor(binding.gameDe5)
        }
        if (binding.gameDe6.contentDescription == "pressed"){
            binding.gameDe6.setImageResource(getDePicture(MyRandom(1, 7)))
            rotateDeColor(binding.gameDe6)
        }
    }

    private fun leaveParis(joueur: Joueur) {
        binding.pionJoueurMain.isClickable = false
        binding.TakeParisButton.isClickable = true

        binding.pionPresident.isVisible = false
        binding.pionJoueurMain.setImageResource(joueur.imageSrc)

    }

    private fun takeParis(joueur: Joueur) {
        binding.pionPresident.isVisible = true
        binding.TakeParisButton.isClickable = false
        binding.pionJoueurMain.isClickable = true

        PlayerInTheMiddle = joueur

        binding.pionPresident.setImageResource(joueur.imageSrc)
        binding.pionJoueurMain.setImageResource(R.drawable.away)
    }

    private fun BotTurn(id: Int) {
        binding.gameStatus.text = playerBot[id].name + " joue"
        getMyDe()
        println("sleep")
        Thread.sleep(2000)
        println("stop sleep")
    }

    private fun MyRandom(min: Int, max: Int): Int {
        return Random.nextInt(min, max)
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}