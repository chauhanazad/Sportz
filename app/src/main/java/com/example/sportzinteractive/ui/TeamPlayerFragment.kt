package com.example.sportzinteractive.ui

import android.os.Bundle
import android.view.*
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportzinteractive.R
import com.example.sportzinteractive.databinding.FragmentTeamPlayerBinding
import com.example.sportzinteractive.network.model.Team
import com.example.sportzinteractive.ui.base.UiState
import com.example.sportzinteractive.viewmodel.SharedViewModel
import okhttp3.internal.filterList
import java.util.*

class TeamPlayerFragment : Fragment() {

    private lateinit var binding: FragmentTeamPlayerBinding
    private lateinit var sharedViewModel: SharedViewModel
    lateinit var adapter: TeamsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTeamPlayerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvTeams.layoutManager = LinearLayoutManager(requireContext())
        adapter = TeamsAdapter(requireContext(), ArrayList())
        binding.rvTeams.adapter = adapter
    }

    private fun setupViewModel() {
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        sharedViewModel.responseData.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    binding.loader.visibility = View.GONE
                    renderList(it.data.teams)
                    binding.rvTeams.visibility = View.VISIBLE
                }

                is UiState.Loading -> {
                    binding.loader.visibility = View.VISIBLE
                    binding.rvTeams.visibility = View.GONE
                }

                is UiState.Error -> {
                    //Handle Error
                    binding.loader.visibility = View.GONE
                    Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_LONG)
                        .show()
                }

                else -> {

                }
            }
        }
    }

    private fun renderList(teams: Map<String, Team>?) {
        val teamPlayerList: ArrayList<Team> = ArrayList()
        val filterData: ArrayList<String> = ArrayList()
        filterData.add("All")
        teams?.entries?.forEach {
            teamPlayerList.add(it.value)
            filterData.add(it.value.nameFull!!)
        }
        adapter.updateList(teamPlayerList)
        binding.filter.adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            filterData)
        binding.filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2!=0) {
                    adapter.updateList(ArrayList(teamPlayerList.filterList {
                        nameFull == filterData[p2]
                    }))
                }
                else
                {
                    adapter.updateList(teamPlayerList)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }
}