package com.example.ejercicioparcial.gui.utilities

import com.example.ejercicioparcial.database.entities.Match

interface MyAdapter {

    fun changeDataSet(newDataSet : List<Match>)
}