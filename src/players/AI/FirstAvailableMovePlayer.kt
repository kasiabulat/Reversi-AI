package players.AI

import board.Board
import players.Player

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class FirstAvailableMovePlayer(override val name: String) :Player {
	override fun makeMove(board: Board): Int {
		for(move in 0..63)
		{
			if(board.isCorrectMove(move))
				return move
		}
		throw RuntimeException("Cannot make move")
	}
}