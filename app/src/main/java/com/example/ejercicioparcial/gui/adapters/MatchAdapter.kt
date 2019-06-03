package com.example.ejercicioparcial.gui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioparcial.database.entities.Match
import com.example.ejercicioparcial.R
import com.example.ejercicioparcial.gui.utilities.MyAdapter
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class MatchAdapter(var matches : List<Match>, val clickListener: (Match) -> Unit) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>(), MyAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent,false)

        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matches[position], clickListener)
    }

    override fun changeDataSet(newDataSet: List<Match>) {

        this.matches = newDataSet
        notifyDataSetChanged()
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(match: Match, clickListener: (Match) -> Unit ) = with(itemView){

            teamA.text = match.teamA
            teamB.text = match.teamB
            scoreteamA.text = match.scoreA.toString()
            scoreteamB.text = match.scoreB.toString()

            itemView.setOnClickListener { clickListener(match) }
        }
    }
}