package players.ai

import board.Board
import players.Player

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class MostValuableAvailableMovePlayer(override val name: String) :Player {
	override fun makeMove(board: Board, onMoveDecided: (Int) -> Unit) {
		val moves = (0..63).filter { board.isCorrectMove(it) }.toList()
		var bestValue = 0
		var bestMove = -1
		for(move in moves) {
			val value = board.getMoveValue(move)
			if(value > bestValue) {
				bestMove = move
				bestValue = value
			}
		}
		if(bestMove == -1)
			throw RuntimeException("Cannot make move")
		onMoveDecided(bestMove)
		return
	}
}