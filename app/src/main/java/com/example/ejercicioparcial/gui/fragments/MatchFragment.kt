package com.example.ejercicioparcial.gui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicioparcial.database.entities.Match
import com.example.ejercicioparcial.gui.adapters.MatchAdapter
import com.example.ejercicioparcial.R
import com.example.ejercicioparcial.gui.utilities.MyAdapter
import com.example.ejercicioparcial.viewmodels.MatchViewModel
import kotlinx.android.synthetic.main.content_main.view.*

class MatchFragment  : Fragment(){

    private lateinit var viewModel : MatchViewModel
    private val matchesLis = ArrayList <Match>()
    private lateinit var matchAdapter: MyAdapter
    private var listenerTools: ListenerTools? = null

    interface ListenerTools{
        fun click(match: Match)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.content_main, container,false)

        initRecyclerView(view)

        return view
    }

    private fun initRecyclerView(container: View){
        val linearLayoutManager = LinearLayoutManager(this.context)
        val recyclerView = container.recyclerview_matches

        matchAdapter = MatchAdapter(
            matches = matchesLis,
            clickListener = { match: Match -> listenerTools?.click(match) })
        recyclerView.apply {
            adapter = matchAdapter as MatchAdapter
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }

        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        viewModel.allMatches.observe(this, Observer { matches ->
            matches?.let{
                matchAdapter.changeDataSet(it)
            }
        })

    }

    override fun onDetach() {
        super.onDetach()
        listenerTools = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ListenerTools) {
            listenerTools = context
        } else {
            throw RuntimeException("Se necesita una implementación de la interfaz")
        }
    }
}