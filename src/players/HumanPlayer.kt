package players

import board.Board
import java.io.PrintWriter
import java.util.*

/**
 * Created by Kamil Rajtar on 08.12.17.
 */
class HumanPlayer(private val input: Scanner, private val output: PrintWriter) : Player {

	private fun readCoordinate(name: String): Int {
		output.println(name)
		val rawResult = input.nextLine()
		val trimmedResult = rawResult.trim()
		val userNumber = Integer.valueOf(trimmedResult)
		return userNumber - 1
	}

	override fun makeMove(board: Board): Int {
		try {
			val row = readCoordinate("Row: ")
			val column = readCoordinate("Column: ")
			val cell = Board.getCellNumber(row, column)
			if (!board.isCorrectMove(cell)) {
				output.println("ERROR! Cannot make this move")
				return makeMove(board)
			}
			return cell

		} catch (e: NumberFormatException) {
			output.println("ERROR! Bad number format")
			return makeMove(board)
		}
	}
}