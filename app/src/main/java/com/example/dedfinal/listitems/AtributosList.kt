package com.example.dedfinal.listitems

import kotlinx.coroutines.flow.MutableStateFlow
import com.example.dedfinal.model.Atributos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class AtributosList {

    private val _atributosList = MutableStateFlow<MutableList<Atributos>>(mutableListOf())
    private val atributosListFlow : StateFlow<MutableList<Atributos>> = _atributosList

    fun getAtributos(): Flow<MutableList<Atributos>> {
        val atributosList: MutableList<Atributos> = mutableListOf(
            Atributos(8, 8, 8, 8, 8, 8),
        )
        _atributosList.value = atributosList
        return atributosListFlow
    }

}