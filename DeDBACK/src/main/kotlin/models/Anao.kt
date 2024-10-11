package models

import models.Atributos
import services.RacaStrategy
class Anao : RacaStrategy {
    override fun aplicarBonus(atributos: Atributos) {
        val bonus = mapOf(
            "constituicao" to 2
        )
        atributos.aplicarBonusRacial(bonus)
    }
}
