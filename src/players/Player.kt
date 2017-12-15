package players

import board.Board

/**
 * Created by Kamil Rajtar on 08.12.17.
 */
interface Player {
	val name: String
	fun makeMove(board: Board, onMoveDecided: (Int) -> Unit)
}