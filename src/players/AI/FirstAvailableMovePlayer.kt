package players.AI

import board.Board
import players.Player

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class FirstAvailableMovePlayer(override val name: String) :Player {
	override fun makeMove(board: Board, onMoveDecided: (Int) -> Unit) {
		for(move in 0..63)
		{
			if(board.isCorrectMove(move)) {
				onMoveDecided(move)
				return
			}
		}
		throw RuntimeException("Cannot make move")
	}
}