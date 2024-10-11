package models

import models.Atributos
import services.RacaStrategy
class Elfo : RacaStrategy {
    override fun aplicarBonus(atributos: Atributos) {
        val bonus = mapOf(
            "destreza" to 2
        )
        atributos.aplicarBonusRacial(bonus)
    }
}
