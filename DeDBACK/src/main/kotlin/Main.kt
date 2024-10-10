import models.Personagem
import models.Atributos
import models.Anao
import models.Orc
import models.Gnomo
import models.MeioElfo
import services.*

fun main() {
    println("\n**Bem-Vindo ao Dungeons&Dragons**")

    val distribuicao = DistribuicaoAtributos()

    distribuicao.mostrarTabelaPontos()

    val nome = readName()

    fun escolherRaca(): RacaStrategy {
        println("\nEscolha a raça do personagem:")
        println("1 - Elfo")
        println("2 - Humano")
        println("3 - Anão")
        println("4 - Orc")
        println("5 - Gnomo")
        println("6 - Meio-Elfo")
        print("Digite o número correspondente à raça escolhida: ")
        return when (readLine()) {
            "1" -> Elfo()
            "2" -> Humano()
            "3" -> Anao()
            "4" -> Orc()
            "5" -> Gnomo()
            "6" -> MeioElfo()
            else -> {
                println("Opção inválida. Humano selecionado por padrão.")
                Humano()
            }
        }
    }


    fun escolherAtributos(): Atributos {
        val atributos = Atributos()
        var pontosRestantes = distribuicao.pontosTotais

        while(pontosRestantes!=0) {
            fun setAtributo(nome: String): Int {
                while (true) {
                    println("Escolha o valor para $nome (8-15). Pontos restantes: $pontosRestantes")
                    val input = readLine()?.toIntOrNull()
                    val custo = input?.let { distribuicao.custoPontos.getOrDefault(it, 0) } ?: 0
                    if (input != null && input in 8..15 && (pontosRestantes - custo) >= 0) {
                        pontosRestantes -= custo
                        return input
                    } else {
                        println("Valor inválido ou pontos insuficientes! O valor para $nome deve estar entre 8 e 15 e você deve ter pontos suficientes.")
                    }
                }
            }

            atributos.forca = setAtributo("Força")
            atributos.destreza = setAtributo("Destreza")
            atributos.constituicao = setAtributo("Constituição")
            atributos.inteligencia = setAtributo("Inteligência")
            atributos.sabedoria = setAtributo("Sabedoria")
            atributos.carisma = setAtributo("Carisma")
            if(pontosRestantes>0){
                println("Você ainda tem pontos pra gastar. Tente de novo.")
                val atributos = null
                pontosRestantes= 27
            }
        }
        return atributos
    }

    fun criarPersonagem(): Personagem {
        val raca = escolherRaca()
        val atributos = escolherAtributos()

        val pontosGastos = atributos.totalDePontos().let { total ->
            atributos.forca.let { distribuicao.custoPontos.getOrDefault(it, 0) } +
                    atributos.destreza.let { distribuicao.custoPontos.getOrDefault(it, 0) } +
                    atributos.constituicao.let { distribuicao.custoPontos.getOrDefault(it, 0) } +
                    atributos.inteligencia.let { distribuicao.custoPontos.getOrDefault(it, 0) } +
                    atributos.sabedoria.let { distribuicao.custoPontos.getOrDefault(it, 0) } +
                    atributos.carisma.let { distribuicao.custoPontos.getOrDefault(it, 0) }
        }

        if (pontosGastos > distribuicao.pontosTotais) {
            throw IllegalArgumentException("Distribuição de pontos excede o máximo permitido.")
        }

        val personagem = Personagem(
            nome = nome,
            raca = raca,
            forca = atributos.forca,
            destreza = atributos.destreza,
            constituicao = atributos.constituicao,
            inteligencia = atributos.inteligencia,
            sabedoria = atributos.sabedoria,
            carisma = atributos.carisma
        )

        personagem.aplicarBonusRacial()

        val pontosRestantes = distribuicao.pontosTotais - pontosGastos
        println("\nPontos totais gastos: $pontosGastos")
        println("Pontos restantes: $pontosRestantes")

        println("\n**Personagem criado**")
        println("Nome: ${personagem.nome} ")
        println("Raça: ${personagem.raca::class.simpleName}")
        println("Força: ${personagem.forca}")
        println("Destreza: ${personagem.destreza}")
        println("Constituição: ${personagem.constituicao}")
        println("Inteligência: ${personagem.inteligencia}")
        println("Sabedoria: ${personagem.sabedoria}")
        println("Carisma: ${personagem.carisma}")
        println("Pontos de Vida: ${personagem.pontosDeVida}")

        personagem.mostrarBonusRacial()

        return personagem
    }

    criarPersonagem()
}
