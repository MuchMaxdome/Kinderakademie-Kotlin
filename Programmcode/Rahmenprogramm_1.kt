import java.lang.Exception
import java.util.*

fun main(args: Array<String>){

    var x: Int? = null

    println("Spieler 1 gib bitte eine Zahl zwischen 1-100 ein: ")

    while (x == null) {
        try {
            x = readLine()?.toInt()
            if (x!! > 100 || x!! < 1) { x = null; println("Die Zahl war nicht in dem Bereich 1-100") }
        }
        catch (e: Exception) { println("Bitte versuche es nochmal Spieler 1") }
    }


    var y: Int? = null

    println("Spieler 2 gib nun bitte auch eine Zahl zwischen 1-100 ein: ")

    while (y == null) {
        try {
            y = readLine()?.toInt()
            if (y!! > 100 || y!! < 1) { y = null; println("Die Zahl war nicht in dem Bereich 1-100.") }
            if (y!! == x!!) { y = null; println("Ihr könnt nicht beide die selbe Zahl nehmen.") }
        }
        catch (e: Exception) { println("Bitte versuche es nochmal Spieler 2") }
    }

    var z: Int = Random().nextInt(100)+1

    // eure Variablen zum Arbeiten
    var Spieler1: Int = x
    var Spieler2: Int = y
    var Zufall: Int   = z


    // Schreibt ab hier euer Spiel

}