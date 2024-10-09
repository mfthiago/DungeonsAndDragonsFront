package com.example.dedfinal.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dedfinal.databinding.CharacterCreationBinding
import com.example.dedfinal.model.Atributos
import android.view.LayoutInflater

class AtributoAdapter(private val context: Context, private val atributoList: MutableList<Atributos>):
    RecyclerView.Adapter<AtributoAdapter.AtributoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtributoViewHolder {
        val listItem = CharacterCreationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AtributoViewHolder(listItem)
    }

    override fun getItemCount() = atributoList.size

    override fun onBindViewHolder(holder: AtributoViewHolder, position: Int) {
        holder.forca.setText(atributoList[position].forca.toString())
        holder.destreza.setText(atributoList[position].destreza.toString())
        holder.constituicao.setText(atributoList[position].constituicao.toString())
        holder.inteligencia.setText(atributoList[position].inteligencia.toString())
        holder.sabedoria.setText(atributoList[position].sabedoria.toString())
        holder.carisma.setText(atributoList[position].carisma.toString())
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