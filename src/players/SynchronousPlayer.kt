package players

import board.Board

/**
 * Created by Kamil Rajtar on 08.12.17.
 */
interface SynchronousPlayer:Player {
	fun makeMove(board:Board):Int
}