package com.example.ejercicioparcial.gui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicioparcial.R
import kotlinx.android.synthetic.main.activity_match_info.*

class MatchInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_info)

        val mIntent = intent
        if(intent!=null){

            var teamA = mIntent.getStringExtra("teamA")
            var teamB = mIntent.getStringExtra("teamB")
            var scoreA = mIntent.getStringExtra("scoreA")
            var scoreB = mIntent.getStringExtra("scoreB")
            var date = mIntent.getStringExtra("date")

            var score1 = scoreA.toInt()
            var score2 = scoreB.toInt()

            teamA_info.text = teamA
            teamB_info.text = teamB

            scoreA_info.text = scoreA
            scoreB_info.text = scoreB

            if(score1 > score2){
                winner_info.text = teamA
            } else{
                winner_info.text = teamB
            }

            date_info.text = date


        }
    }
}
