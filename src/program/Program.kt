package program

import board.BoardFactory
import game.Game
import players.ai.FirstAvailableMovePlayer
import players.ai.RandomizePlayPlayer
import java.io.PrintWriter
import java.util.*

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class Program {

	fun run() {
		val output = PrintWriter(System.out, true)
//		val input = Scanner(System.`in`)
//		val black= HumanTextPlayer("Player 1",input,output)
//		val white= HumanTextPlayer("Player 2",input,output)
//		val black=FirstAvailableMovePlayer("Player 1")
//		val white=FirstAvailableMovePlayer("Player 2")
		val black = FirstAvailableMovePlayer("Player 1")
		val white = RandomizePlayPlayer("Player 2", Random())
		val boardFactory = BoardFactory()
		Game(black, white, boardFactory, output).playGame()
	}


}

fun main(args: Array<String>) {
	Program().run()
}

