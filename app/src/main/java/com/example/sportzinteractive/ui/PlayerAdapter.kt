package com.example.sportzinteractive.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportzinteractive.R
import com.example.sportzinteractive.network.model.PlayerDetail
import java.util.*

class PlayerAdapter (private val context: Context, private var data: ArrayList<PlayerDetail>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>(){

    inner class PlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val playerName: TextView = itemView.findViewById(R.id.playerName)
        val playerStatus: TextView = itemView.findViewById(R.id.playerStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_player,parent,false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.playerName.text = data[position].NameFull
        if (data[position].isCaptain && data[position].isKeeper)
        {
            holder.playerStatus.text = context.getString(R.string.captainandkeeper,"c","wk")
            holder.playerStatus.visibility= View.VISIBLE
        }
        else if(data[position].isCaptain)
        {
            holder.playerStatus.text = context.getString(R.string.captainorkeeper,"c")
            holder.playerStatus.visibility= View.VISIBLE
        }
        else if(data[position].isKeeper)
        {
            holder.playerStatus.text = context.getString(R.string.captainorkeeper,"wk")
            holder.playerStatus.visibility= View.VISIBLE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(teamPlayerList: ArrayList<PlayerDetail>) {
        data = teamPlayerList
        notifyDataSetChanged()
    }
}