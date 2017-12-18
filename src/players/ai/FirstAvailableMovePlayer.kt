package players.ai

import board.Board
import players.SynchronousPlayer

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class FirstAvailableMovePlayer(override val name: String) :SynchronousPlayer {
	override fun makeMove(board: Board):Int {
		(0..63)
				.filter {board.isCorrectMove(it)}
				.forEach {return it}
		throw RuntimeException("Cannot make move")
	}
}