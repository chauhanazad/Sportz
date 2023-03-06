package com.example.sportzinteractive.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportzinteractive.R
import com.example.sportzinteractive.network.model.PlayerDetail
import com.example.sportzinteractive.network.model.Team
import java.util.*

class TeamsAdapter(private val context: Context, private var data: ArrayList<Team>) :
    RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {
    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerTitle: TextView = itemView.findViewById(R.id.header_title)
        val rvPlayer: RecyclerView = itemView.findViewById(R.id.rvPlayers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_teams, parent, false)
        return TeamViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.headerTitle.text = data[position].nameFull
        holder.rvPlayer.layoutManager = LinearLayoutManager(context)
        val playerList: ArrayList<PlayerDetail> = ArrayList()
        data[position].player?.forEach { s, playerDetail ->
            playerList.add(playerDetail)
        }
        val adapter = PlayerAdapter(context, playerList)
        holder.rvPlayer.adapter = adapter

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(teamPlayerList: ArrayList<Team>) {
        data = teamPlayerList
        notifyDataSetChanged()
    }
}