package players

import board.Board
import java.io.PrintWriter
import java.util.*

/**
 * Created by Kamil Rajtar on 08.12.17.
 */
class HumanPlayer(override val name: String, private val input: Scanner, private val output: PrintWriter) : Player {

	private fun readCoordinate(name: String): Int {
		output.print(name)
		output.flush()
		val rawResult = input.nextLine()
		val trimmedResult = rawResult.trim()
		val userNumber = Integer.valueOf(trimmedResult)
		return userNumber - 1
	}

	override fun makeMove(board: Board, onMoveDecided: (Int) -> Unit) {
		try {
			val row = readCoordinate("Row: ")
			val column = readCoordinate("Column: ")
			val cell = Board.getCellNumber(row, column)
			if (!board.isCorrectMove(cell)) {
				output.println("ERROR! Cannot make this move")
				makeMove(board, onMoveDecided)
				return
			}
			onMoveDecided(cell)
			return
		} catch (e: NumberFormatException) {
			output.println("ERROR! Bad number format")
			makeMove(board, onMoveDecided)
			return
		}
	}
}