package models

data class Atributos(
    var forca: Int = 8,
    var destreza: Int = 8,
    var constituicao: Int = 8,
    var inteligencia: Int = 8,
    var sabedoria: Int = 8,
    var carisma: Int = 8
) {

    fun totalDePontos(): Int {
        return forca + destreza + constituicao + inteligencia + sabedoria + carisma
    }


    fun aplicarBonusRacial(bonus: Map<String, Int>) {
        bonus.forEach { (atributo, incremento) ->
            when (atributo.lowercase()) {
                "forca" -> forca += incremento
                "destreza" -> destreza += incremento
                "constituicao" -> constituicao += incremento
                "inteligencia" -> inteligencia += incremento
                "sabedoria" -> sabedoria += incremento
                "carisma" -> carisma += incremento
            }
        }
    }
}
