package program

import asynchronous

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class Program {

	fun run()
	{
		val input=Scanner(System.`in`)
		val output=PrintWriter(System.out,true)
		val black=HumanPlayer("Player 1",input,output)
		val white= HumanPlayer("Player 2",input,output)
//		val black=FirstAvailableMovePlayer("Player 1")
//		val white=FirstAvailableMovePlayer("Player 2")
		val boardFactory=BoardFactory()
		Game(black,white,boardFactory,output).playGame()
	}


}

public fun main(args:Array<String>):Unit
{
	Program().run()
}

