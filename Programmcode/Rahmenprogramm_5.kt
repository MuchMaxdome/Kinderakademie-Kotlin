import kotlin.concurrent.thread
var globalHit: String? = null

fun hitPit(): Int{
    var y = 0
    var z: Boolean = false

    thread(true,false,null,null,-1,{
        globalHit = readLine()
    })

    while (globalHit == null){
        if      (y == 20) z = true
        else if (y ==  1) z = false

        y = if (z) y - 1 else y + 1

        Thread.sleep(5_0)

        for (i in 0..20){
            if (i == y) print(" | $y | ")
            else        print(i)
        }

        println()
    }

    globalHit = null
    return y
}

fun drawBoard(x: IntArray){
    print("\n \n \n \n \n \n \n \n \n \n")

    for (j in 0..3) {
        for (i in 0..100) {
            if (x[j] == i) print("X")
            else print("-")
        }
        println("\n \n")
    }
}

// ab hier dürft ihr euer Programm schreiben

fun main(args: Array<String>){

}



