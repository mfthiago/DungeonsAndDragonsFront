package services

fun readName(): String {
    println("Digite o nome do seu personagem:")
    val input = readLine()
    return if (!input.isNullOrBlank()) {
        input
    } else {
        println("Nome inválido. O nome foi definido como 'Unknown'.")
        "Unknown"
    }
}