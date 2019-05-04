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
    var dif1 = Spieler1 - Zufall
    var dif2 = Spieler2 - Zufall

    if (dif1 < 0) dif1 = -dif1
    if (dif2 < 0) dif2 = -dif2

    if (dif1 < dif2){
        print("Spieler 1 gewinnt. Die Zahl war $Zufall")
    }
    else if (dif2 < dif1){
        print("Spieler 2 gewinnt. Die Zahl war $Zufall")
    }
    else{
        print("Unentschieden. Die Zahl war $Zufall")
    }
}