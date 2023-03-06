package com.example.sportzinteractive.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sportzinteractive.R
import com.example.sportzinteractive.databinding.FragmentHomeBinding
import com.example.sportzinteractive.network.model.ResponseData
import com.example.sportzinteractive.ui.base.UiState
import com.example.sportzinteractive.viewmodel.SharedViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupListener()
    }

    private fun setupListener() {
        binding.btnMore.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_teamPlayerFragment)
        }
    }

    private fun setupViewModel() {
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        sharedViewModel.responseData.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    binding.loader.visibility = View.GONE
                    renderList(it.data)
                    binding.matchDetailContainer.visibility = View.VISIBLE
                }

                is UiState.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                    binding.matchDetailContainer.visibility = View.GONE
                }

                is UiState.Error -> {
                    //Handle Error
                    binding.loader.visibility = View.GONE
                    Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_LONG)
                        .show()
                }

                else -> {}
            }
        }
    }

    private fun renderList(data: ResponseData) {
        binding.matchNumber.text = getString(
            R.string.matchAndVenue, data.matchDetail?.match?.number,
            data.matchDetail?.venue?.Name
        )
        var teamA = ""
        var teamB = ""
        var i = 0
        data.teams?.entries?.forEach {
            if (teamA.isEmpty()) {
                ++i
                teamA = it.value.nameFull!!
            }
            if (teamB.isEmpty() && i!=1)
            {
                if (teamA!=teamB) {
                    teamB = it.value.nameFull!!
                }
            }
            ++i
        }

        binding.matchTitle.text = getString(R.string.matchTitle, teamA, teamB)

        binding.matchDateTime.text = getString(
            R.string.matchDateTime, data.matchDetail?.match?.date,
            data.matchDetail?.match?.time, data.matchDetail?.match?.offset
        )
    }
}