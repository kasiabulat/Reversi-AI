package players

import board.Board

/**
 * Created by Kamil Rajtar on 18.12.17.
 */
interface AsynchronousPlayer:Player {
	fun makeMove(board:Board,onMoveDecided:(Int)->Unit)
}