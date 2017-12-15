package players.ai

import board.Board
import players.Player
import java.util.*

/**
 * Created by Kamil Rajtar on 15.12.17.  */
class RandomizePlayPlayer(override val name: String, private val random: Random) : Player {
	private fun evaluateBoard(board: Board): Int {
		var boardCurrent = board
		var resultMultiplier = 1;
		if (boardCurrent.isGameEnded()) {
			return when (boardCurrent.getDominatingSite()) {
				Board.Site.PLAYER -> 1
				Board.Site.OPPONENT -> -1
				null -> 0
			}
		}
		if (!boardCurrent.canPlayerPutPiece()) {
			boardCurrent = boardCurrent.passTurn()
			resultMultiplier *= -1
		}
		val moves = (0..63).filter { boardCurrent.isCorrectMove(it) }.toList()
		val elementNumber = random.nextInt(moves.size)
		val playedMove = moves[elementNumber]
		boardCurrent = boardCurrent.makeMove(playedMove)
		return -(evaluateBoard(boardCurrent) * resultMultiplier)
	}

	override fun makeMove(board: Board, onMoveDecided: (Int) -> Unit) {

		val decidedMove = (0..63).filter { board.isCorrectMove(it) }.parallelStream().map {
			var result = 0
			val currentBoard = board.makeMove(it)
			repeat(NUMBER_OF_TRIES_PER_CELL)
			{
				result += evaluateBoard(currentBoard)
			}
			return@map it to result
		}.min { A, B -> A.second.compareTo(B.second) }.map { it.first }.get()

		onMoveDecided(decidedMove)
	}

	companion object {
		private const val NUMBER_OF_TRIES_PER_CELL = 1000
	}
}
