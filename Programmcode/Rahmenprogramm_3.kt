import java.util.*

fun main(args: Array<String>){
    // hier kommt euer Code hin

}

fun dice(): Int = Random().nextInt(6)+1

fun waitforPlayer1(){
    var x = false

    println("Spieler 1 würfel bitte")

    while (x == false) {
        try { readLine(); x = true }
        catch (e: Exception) { println("Bitte versuche es nochmal Spieler 1") }
    }
}

fun waitforPlayer2(){
    var x = false

    println("Spieler 2 würfel bitte")

    while (x == false) {
        try { readLine(); x = true }
        catch (e: Exception) { println("Bitte versuche es nochmal Spieler 2") }
    }
}