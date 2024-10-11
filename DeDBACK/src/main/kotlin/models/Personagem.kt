package models

import android.os.Parcel
import android.os.Parcelable
import services.RacaStrategy
import models.*

class Personagem(
    val nome: String,
    val raca: RacaStrategy,
    var forca: Int,
    var destreza: Int,
    var constituicao: Int,
    var inteligencia: Int,
    var sabedoria: Int,
    var carisma: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readParcelable(RacaStrategy::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeParcelable(raca, flags)
        parcel.writeInt(forca)
        parcel.writeInt(destreza)
        parcel.writeInt(constituicao)
        parcel.writeInt(inteligencia)
        parcel.writeInt(sabedoria)
        parcel.writeInt(carisma)
    }

    class Personagem(){

    }

    val modificadorConstituicao: Int
        get() = (constituicao - 10) / 2

    val pontosDeVida: Int
        get() = 10 + modificadorConstituicao

    fun aplicarBonusRacial() {
        val atributos = Atributos(forca, destreza, constituicao, inteligencia, sabedoria, carisma)
        raca.aplicarBonus(atributos)
        forca = atributos.forca
        destreza = atributos.destreza
        constituicao = atributos.constituicao
        inteligencia = atributos.inteligencia
        sabedoria = atributos.sabedoria
        carisma = atributos.carisma
    }

    fun mostrarBonusRacial() {
        println("\nBônus Racial Aplicado:")

        when (raca) {
            is Elfo -> println("Destreza: +2")
            is Humano -> {
                println("Força: +1")
                println("Destreza: +1")
                println("Constituição: +1")
                println("Inteligência: +1")
                println("Sabedoria: +1")
                println("Carisma: +1")
            }
            is Anao -> println("Constituição: +2")
            is Orc -> {
                println("Força: +2")
                println("Constituição: +1")
            }
            is Gnomo -> {
                println("Inteligência: +2")
            }
            is MeioElfo -> {
                println("Carisma: +2")
            }
        }
        fun mostrarBonusRaca() {
            println("\nBônus Racial Aplicado:")

            when (selectedRace) {
                is Elfo -> println("Destreza: +2")
                is Humano -> {
                    println("Força: +1")
                    println("Destreza: +1")
                    println("Constituição: +1")
                    println("Inteligência: +1")
                    println("Sabedoria: +1")
                    println("Carisma: +1")
                }
                is Anao -> println("Constituição: +2")
                is Orc -> {
                    println("Força: +2")
                    println("Constituição: +1")
                }
                is Gnomo -> {
                    println("Inteligência: +2")
                }
                is MeioElfo -> {
                    println("Carisma: +2")
                }
            }
    }
}