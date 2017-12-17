package players.ai

import board.Board
import javafx.concurrent.Task
import players.Player
import java.util.*

/**
 * Created by Kamil Rajtar on 15.12.17.  */
class RandomizePlayPlayer(override val name: String, private val random: Random,private val numberOfTries: Int) : Player {
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

		val task=object :Task<Int>()
		{
			override fun call():Int {
					return (0..63).filter { board.isCorrectMove(it) }.parallelStream().map {
						var result = 0
						val currentBoard = board.makeMove(it)
						repeat(numberOfTries)
						{
							result += evaluateBoard(currentBoard)
						}
						return@map it to result
					}.min { A, B -> A.second.compareTo(B.second) }.map { it.first }.get()
			}

			override fun succeeded() {
				onMoveDecided(value)
			}
		}

		Thread(task).start()
	}
}
