import java.awt.Canvas
import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.image.BufferStrategy
import java.awt.event.*
import java.util.*
import javax.swing.*

enum class lastDirection{
    Left, Right, Up, Down
}

class block(x: Int, y: Int){
    var x = x
    var y = y

    internal var width  = 50
    internal var height = 50
}

class dot(x: Int, y: Int){
    var x = x + 10
    var y = y + 10

    internal var width  = 30
    internal var height = 30
}

class HandlingEvents : Runnable {

    internal var frame: JFrame

    internal var Snake: Array<block?> = Array(30, {i -> null})
    internal var myDot: dot = dot(0,0)
    internal var addBlock: Boolean = false

    internal var myDirection: lastDirection = lastDirection.Right
    internal var keycooldown = false

    internal var canvas: Canvas
    internal var bufferStrategy: BufferStrategy
    internal var running = true

    init {
        frame = JFrame("Basic Game")
        val panel = frame.contentPane as JPanel
        panel.preferredSize = Dimension(500, 500)
        panel.layout = null
        canvas = Canvas()
        canvas.setBounds(0, 0, 500, 500)
        canvas.ignoreRepaint = true
        panel.add(canvas)
        canvas.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(evt: KeyEvent?) {
                moveIt(evt)
            }
        })
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.pack()
        frame.isResizable = false
        frame.isVisible = true
        canvas.createBufferStrategy(2)
        bufferStrategy = canvas.bufferStrategy
        canvas.requestFocus()

        Snake[0] = block(0,0)
        spawnrandomdot()
    }

    private fun addBlock(){
        addBlock = true
    }

    private fun spawnrandomdot(){
        var rng = Random()
        var i = rng.nextInt(99)

        var j = i % 10
        i /= 10

        myDot.x = i*50 + 10
        myDot.y = j*50 + 10
    }

    override fun run() {
        while (running == true) {
            Paint()
            try {
                Thread.sleep(125)
                automove()
                keycooldown = false
            } catch (e: InterruptedException) { }
        }
    }

    private fun Paint() {
        val g = bufferStrategy.drawGraphics as Graphics2D
        g.clearRect(0, 0, 500, 500)
        Paint(g)
        bufferStrategy.show()
    }

    private fun Paint(g: Graphics2D) {
        for (i in 0..19) {
            if (Snake[i] != null) g.fillRect(Snake[i]!!.x, Snake[i]!!.y, Snake[i]!!.width, Snake[i]!!.height)
        }
        g.fillOval(myDot.x,myDot.y,myDot.width,myDot.height)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val ex = HandlingEvents()
            Thread(ex).start()
        }
    }

    // Gibt die X Koordinate vom Punkt zurück
    fun getdotX(): Int{
        return myDot.x - 10
    }

    // Gibt die Y Koordinate vom Punkt zurück
    fun getdotY(): Int{
        return myDot.y - 10
    }

    // Gibt die X Koordinate an i-ter Stelle aus
    fun getSnakeX(): Int{
        return Snake[0]!!.x
    }

    // Gibt die Y Koordinate an i-ter Stelle aus
    fun getSnakeY(): Int{
        return Snake[0]!!.y
    }

    fun automove(){
        if (gameWon()) { running = false; print("Spiel gewonnen") ; return }
        if (gameLost()) { running = false; print("Spiel verloren") ; return }

        for (i in 19 downTo 1) {
            if      (Snake[i] != null)                                     { Snake[i]!!.x = Snake[i-1]!!.x; Snake[i]!!.y = Snake[i-1]!!.y }
            else if (Snake[i] == null && addBlock && Snake[i-1] != null)   { Snake[i] = block(Snake[i-1]!!.x,Snake[i-1]!!.y); addBlock = false;}
        }

         when (myDirection) {
             lastDirection.Right -> Snake[0]!!.x += 50
             lastDirection.Left  -> Snake[0]!!.x -= 50
             lastDirection.Up    -> Snake[0]!!.y -= 50
             lastDirection.Down  -> Snake[0]!!.y += 50
         }

        if (hitDot()) { addBlock(); spawnrandomdot()}
    }

    fun moveIt(evt: KeyEvent?) {
        when (evt?.keyCode) {
            KeyEvent.VK_DOWN  -> {if (myDirection != lastDirection.Up)    myDirection = lastDirection.Down;  keycooldown = true }
            KeyEvent.VK_UP    -> {if (myDirection != lastDirection.Down)  myDirection = lastDirection.Up;    keycooldown = true }
            KeyEvent.VK_LEFT  -> {if (myDirection != lastDirection.Right) myDirection = lastDirection.Left;  keycooldown = true }
            KeyEvent.VK_RIGHT -> {if (myDirection != lastDirection.Left)  myDirection = lastDirection.Right; keycooldown = true }
        }
    }

    fun hitBorder(): Boolean{
        when(myDirection) {
            lastDirection.Right -> return Snake[0]!!.x + 50 >= 500
            lastDirection.Left  -> return Snake[0]!!.x - 50 < 0
            lastDirection.Up    -> return Snake[0]!!.y - 50 < 0
            lastDirection.Down  -> return Snake[0]!!.y + 50 >= 500
        }
    }

    fun hitDot(): Boolean{
        return getSnakeY() == getdotY() && getSnakeX() == getdotX()
    }

    fun hitTail(): Boolean{
        for (i in 1..19){
            if (Snake[0]!!.x == Snake[i]?.x && Snake[0]!!.y == Snake[i]?.y) return true
        }
        return false
    }

    fun gameLost(): Boolean {
        return hitBorder() || hitTail()
    }

    fun gameWon(): Boolean {
        if (Snake[29] != null) return true
        return false
    }
}


