package program

import board.BoardFactory
import game.Game
import players.AsynchronousPlayerWrapper
import players.ai.FirstAvailableMovePlayer
import players.ai.RandomizePlayPlayer
import java.io.PrintWriter
import java.util.*

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class Program {

	fun run() {
		val black = AsynchronousPlayerWrapper( FirstAvailableMovePlayer("Player 1"))
		val white = AsynchronousPlayerWrapper( RandomizePlayPlayer("Player 2", Random(123),100))
		val boardFactory = BoardFactory()
		val output = PrintWriter(System.out, true)
		val textUi=TextUI(output,black,white)
		Game(black, white, boardFactory, textUi).playGame()
	}


}

fun main(args: Array<String>) {
	Program().run()
}

