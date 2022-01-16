package com.epita.epitaelection.View.Game

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.epita.epitaelection.R
import com.epita.epitaelection.databinding.UserInflowFragmentWelcomeBinding
import com.epita.epitaelection.databinding.WinViewFragmentBinding

class winView : Fragment() {

    private var _binding: WinViewFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WinViewFragmentBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val winViewArgs: winViewArgs by navArgs()

        binding.GGWINNER.text = "Bravo " + winViewArgs.winner + " vous etes president"

        if (winViewArgs.winner == "Macron")
            binding.imageView15.setImageResource(R.drawable.pion_macron)
        else if(winViewArgs.winner == "Marine"){
            binding.imageView15.setImageResource(R.drawable.pion_lepen)
        }else if(winViewArgs.winner == "Zemour"){
            binding.imageView15.setImageResource(R.drawable.pion_zemour)
        }else if(winViewArgs.winner == "Melenchon"){
            binding.imageView15.setImageResource(R.drawable.pion_melenchon)
        }

        binding.restartButton.setOnClickListener() {
            findNavController().navigate(R.id.action_winView_to_FirstFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}