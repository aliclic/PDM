package com.example.diadasemana.Model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Calendario {

    private var data: Calendar = Calendar.getInstance()
    val dias = arrayListOf("DOMINGO", "SEGUNDA", "TERÇA", "QUARTA", "QUINTA", "SEXTA", "SÁBADO")

    fun diaDaSemana(): String {
        return dias[this.data.get(Calendar.DAY_OF_WEEK) - 1]
    }

    fun diaDaSemanaPorData(dia: Int, mes: Int, ano: Int): String {
        data.set(ano, mes - 1, dia)
        return dias[this.data.get(Calendar.DAY_OF_WEEK) - 1]
    }

}