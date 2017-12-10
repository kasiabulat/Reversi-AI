package program

import board.BoardFactory
import game.Game
import players.HumanPlayer
import java.io.PrintWriter
import java.util.*

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class Program {

	fun run()
	{
		val input=Scanner(System.`in`)
		val output=PrintWriter(System.out,true)
		val black=HumanPlayer("Player 1",input,output)
		val white=HumanPlayer("Player 2",input,output)
		val boardFactory=BoardFactory()
		Game(black,white,boardFactory,output).playGame()
	}


}

public fun main(args:Array<String>):Unit
{
	Program().run()
}

