package com.example.ejercicioparcial.gui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.ejercicioparcial.R
import com.example.ejercicioparcial.viewmodels.ExtraViewModel
import kotlinx.android.synthetic.main.activity_new_match.*
import java.text.SimpleDateFormat
import java.util.*

class NewMatchActivity : AppCompatActivity() {

    private lateinit var extraViewModel : ExtraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_match)

        extraViewModel = ViewModelProviders.of(this).get(ExtraViewModel::class.java)

        displayScore(
            tv_score_team_a,
            extraViewModel.scoreTeamA
        )

        displayScore(
            tv_score_team_b,
            extraViewModel.scoreTeamB
        )

        val today : Calendar = Calendar.getInstance()

        val date:String  = SimpleDateFormat().format(today.time)

        val mIntent = intent

        if(intent!=null){
            val name1 = mIntent.getStringExtra("name1")
            val name2 = mIntent.getStringExtra("name2")

            teamA_name.text = name1
            teamB_name.text = name2

        }
        bt_save.setOnClickListener {

            val intent = Intent()

            intent.putExtra("nameA", teamA_name.text.toString())
            intent.putExtra("nameB", teamB_name.text.toString())
            intent.putExtra("scoreA", extraViewModel.scoreTeamA.toString())
            intent.putExtra("scoreB", extraViewModel.scoreTeamB.toString())
            intent.putExtra("date", date)

            setResult(Activity.RESULT_OK, intent)

            finish()
        }

        bt_team_a_free_throw.setOnClickListener {
            displayScore(
                tv_score_team_a,
                ++extraViewModel.scoreTeamA
            )
        }

        bt_team_b_free_throw.setOnClickListener {
            displayScore(
                tv_score_team_b,
                ++extraViewModel.scoreTeamB
            )
        }

        bt_team_a_2_p.setOnClickListener {
            extraViewModel.scoreTeamA += 2
            displayScore(
                tv_score_team_a,
                extraViewModel.scoreTeamA
            )
        }

        bt_team_b_2_p.setOnClickListener {
            extraViewModel.scoreTeamB += 2
            displayScore(
                tv_score_team_b,
                extraViewModel.scoreTeamB
            )
        }

        bt_team_a_3_p.setOnClickListener {
            extraViewModel.scoreTeamA += 3
            displayScore(
                tv_score_team_a,
                extraViewModel.scoreTeamA
            )
        }

        bt_team_b_3_p.setOnClickListener {
            extraViewModel.scoreTeamB += 3
            displayScore(
                tv_score_team_b,
                extraViewModel.scoreTeamB
            )
        }

        bt_reset.setOnClickListener {
            extraViewModel.scoreTeamA = 0
            extraViewModel.scoreTeamB = 0

            displayScore(
                tv_score_team_a,
                extraViewModel.scoreTeamA
            )

            displayScore(
                tv_score_team_b,
                extraViewModel.scoreTeamB
            )
        }

    }

    fun displayScore(v: TextView, score: Int) {
        v.text = score.toString()
    }


}
