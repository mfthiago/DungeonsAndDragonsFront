package com.example.dedfinal


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dedfinal.data.entities.CharacterEntity


class CharacterAdapter(
    private val onDeleteClick: (CharacterEntity) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var characters: List<CharacterEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = characters[position]
        holder.nameTextView.text = current.nome
        holder.raceTextView.text = current.raca

        holder.deleteButton.setOnClickListener {
            onDeleteClick(current)
        }
    }

    override fun getItemCount() = characters.size

    fun setCharacters(characters: List<CharacterEntity>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val raceTextView: TextView = itemView.findViewById(R.id.raceTextView)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }
}