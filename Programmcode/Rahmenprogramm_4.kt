import java.util.*

fun drawCard() = Random().nextInt(10)+2

fun askPlayer1(y: Int): Boolean{
    println("Spieler1: Dein bisheriger Wert ist $y. Möchtest du ziehen ? (Ja oder Nein)")

    var x: String? = readLine()

    if (x.equals("Ja"))        return true
    else if (x.equals("Nein")) return false
    else                             return askPlayer1(y)
}

fun askPlayer2(y: Int): Boolean{
    println("Spieler2: Dein bisheriger Wert ist $y. Möchtest du ziehen ? (Ja oder Nein)")

    var x: String? = readLine()

    if (x.equals("Ja"))        return true
    else if (x.equals("Nein")) return false
    else                             return askPlayer2(y)
}


// ab hier dürft ihr selbst arbeiten. Die Funktionen, die ihr benutzen dürft sind:
// -> askPlayer1() <- in die Klammer muss euer Punktestand
// -> askPlayer2() <- in die Klammer muss euer Punktestand
// -> drawCard()

fun main(args: Array<String>){
    askPlayer1(1)
    askPlayer2(5)
}




