package com.epita.epitaelection.View.CharacterChoice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.epita.epitaelection.R
import com.epita.epitaelection.View.Game.GameFragment
import com.epita.epitaelection.databinding.UserInflowFragmentCharacterChoiceBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: UserInflowFragmentCharacterChoiceBinding? = null
    private val binding get() = _binding!!
    private var dataSend = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserInflowFragmentCharacterChoiceBinding.inflate(inflater, container, false)
      return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectChallenger()
    }

    private fun selectChallenger(){
        val macronButton = view?.findViewById(R.id.macron_button) as ImageButton
        val lepenButton = view?.findViewById(R.id.marine_button) as ImageButton
        val lezButton = view?.findViewById(R.id.zemour_button) as ImageButton
        val melenchonButton = view?.findViewById(R.id.melenchon_button) as ImageButton

        macronButton.setOnClickListener {characterChosen("Macron")}
        lepenButton.setOnClickListener {characterChosen("THE PEN")}
        lezButton.setOnClickListener {characterChosen("Lord Z")}
        melenchonButton.setOnClickListener {characterChosen("TikTok MASTA")}

    }

    private fun characterChosen(characterChosen: String){
        val charactereChosenText = view?.findViewById(R.id.CharactereChosenTextView) as TextView
        val validateButton = view?.findViewById(R.id.ValidateButton) as Button

        charactereChosenText.text = "$characterChosen je te choisis"
        charactereChosenText.isVisible = true
        validateButton.isVisible = true

        binding.CandidatPicture.isVisible = true
        if (characterChosen == "Macron"){
            binding.CandidatPicture.setImageResource(R.drawable.pion_macron)
            dataSend = "Macron"
        }else if (characterChosen == "THE PEN"){
            binding.CandidatPicture.setImageResource(R.drawable.pion_lepen)
            dataSend = "Marine"
        }else if (characterChosen == "Lord Z"){
            binding.CandidatPicture.setImageResource(R.drawable.pion_zemour)
            dataSend = "Zemour"
        }else if (characterChosen == "TikTok MASTA") {
            binding.CandidatPicture.setImageResource(R.drawable.pion_melenchon)
            dataSend = "Melenchon"
        }

        validateButton.setOnClickListener {
            val action = SecondFragmentDirections.actionSecondFragmentToGameFragment(dataSend)
            findNavController().navigate(action)
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}