package services

import models.Atributos

class DistribuicaoAtributos {

    val pontosTotais = 27


    val custoPontos = mapOf(
        8 to 0,
        9 to 1,
        10 to 2,
        11 to 3,
        12 to 4,
        13 to 5,
        14 to 7,
        15 to 9
    )

    fun mostrarTabelaPontos() {
        println("\nTabela de Custo de Pontos:")
        custoPontos.forEach { (valor, custo) ->
            println("Valor: $valor - Custo: $custo pontos")
        }
    }


    fun distribuirPontos(atributos: Atributos): Boolean {
        val pontosGastos = atributos.totalDePontos().let { total ->

            atributos.forca.let { custoPontos.getOrDefault(it, 0) } +
                    atributos.destreza.let { custoPontos.getOrDefault(it, 0) } +
                    atributos.constituicao.let { custoPontos.getOrDefault(it, 0) } +
                    atributos.inteligencia.let { custoPontos.getOrDefault(it, 0) } +
                    atributos.sabedoria.let { custoPontos.getOrDefault(it, 0) } +
                    atributos.carisma.let { custoPontos.getOrDefault(it, 0) }
        }

        return pontosGastos <= pontosTotais
    }
}
