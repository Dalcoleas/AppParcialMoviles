package com.example.ejercicioparcial.gui.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.ejercicioparcial.database.entities.Match
import com.example.ejercicioparcial.gui.fragments.MatchFragment
import com.example.ejercicioparcial.R
import com.example.ejercicioparcial.viewmodels.MatchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.new_match.*
import kotlinx.android.synthetic.main.recyclerview_item.*

class MainActivity : AppCompatActivity(), MatchFragment.ListenerTools {

    private lateinit var frag : MatchFragment
    private lateinit var matchViewModel: MatchViewModel
    private var resource = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        initMainFragment()
    }

    override fun click(match: Match) {
        val intent = Intent(this@MainActivity, MatchInfoActivity::class.java)
        intent.putExtra("teamA", match.teamA)
        intent.putExtra("teamB", match.teamB)
        intent.putExtra("scoreA", match.scoreA.toString())
        intent.putExtra("scoreB", match.scoreB.toString())
        intent.putExtra("date", match.date)

        startActivity(intent)
    }

    fun initMainFragment(){


        frag = MatchFragment()

        resource = R.id.main_fragment

        supportFragmentManager.beginTransaction().replace(resource,frag).commit()

        bt_add_activity.setOnClickListener{
            val mBuilder = AlertDialog.Builder(this@MainActivity)
            val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.new_match, null)
            val btnAction = view.findViewById<Button>(R.id.btnStartGame)
            val txt1 = view.findViewById<TextView>(R.id.teamASelect)
            val txt2 = view.findViewById<TextView>(R.id.teamBSelect)
            mBuilder.setView(view)
            val dialog = mBuilder.create()
            dialog.show()

            btnAction.setOnClickListener {
                val intent = Intent(this@MainActivity, NewMatchActivity::class.java)
                intent.putExtra("name1", txt1.text.toString())
                intent.putExtra("name2", txt2.text.toString())
                startActivityForResult(intent, newMatchActivityRequestCode)
                dialog.dismiss()
            }
        }
        }


    companion object{
        const val newMatchActivityRequestCode = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newMatchActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.let{
                Log.d("algo", it.toString())
                matchViewModel.insert(Match(it.getStringExtra("nameA"),it.getStringExtra("nameB"),it.getStringExtra("scoreA").toInt(),it.getStringExtra("scoreB").toInt(),it.getStringExtra("date")))
            }
        }
    }


}
