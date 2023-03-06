package com.example.sportzinteractive.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.sportzinteractive.R
import com.example.sportzinteractive.network.model.PlayerDetail
import java.util.*

class PlayerAdapter (private val context: Context, private var data: ArrayList<PlayerDetail>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>(){

    inner class PlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val playerName: TextView = itemView.findViewById(R.id.playerName)
        val playerStatus: TextView = itemView.findViewById(R.id.playerStatus)
        private val playerContainer: ConstraintLayout = itemView.findViewById(R.id.playerContainer)
        init {
            playerContainer.setOnClickListener {
                val dialog = Dialog(context)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(false)
                dialog.setContentView(R.layout.dialog)

                val playerName = dialog.findViewById<View>(R.id.playerName) as TextView
                playerName.setText(data[adapterPosition].NameFull)
                val playerStyle = dialog.findViewById<View>(R.id.playerDetails) as TextView
                playerStyle.setText(context.getString(R.string.battingnbowlingstyle,
                    if (data[adapterPosition].Batting?.Style == "") "N/A" else data[adapterPosition].Batting?.Style,
                    if (data[adapterPosition].Bowling?.Style == "") "N/A" else data[adapterPosition].Bowling?.Style
                ))

                val dialogButton: Button = dialog.findViewById<View>(R.id.btn_dialog) as Button
                dialogButton.setOnClickListener(View.OnClickListener { dialog.dismiss() })

                dialog.show()
//                onClick.invoke(adapterPosition,data[adapterPosition])
            }
        }
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