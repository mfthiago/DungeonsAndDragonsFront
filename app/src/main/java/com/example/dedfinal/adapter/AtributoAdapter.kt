package com.example.dedfinal.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.dedfinal.databinding.AtributoItemBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.dedfinal.databinding.CharacterCreationBinding
import com.example.dedfinal.model.Atributos


class AtributoAdapter(private val context: Context, private val atributoList: MutableList<Atributos>):
    RecyclerView.Adapter<AtributoAdapter.AtributoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtributoViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AtributoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    inner class AtributoViewHolder(binding: CharacterCreationBinding): RecyclerView.ViewHolder(binding.root) {
        var forca = binding.forcaValor
        var destreza = binding.destrezaValor
        var constituicao = binding.constituicaoValor
        var inteligencia = binding.inteligenciaValor
        var sabedoria = binding.sabedoriaValor
        var carisma = binding.carismaValor
        

    }


}