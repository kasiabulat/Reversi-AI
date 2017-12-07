/**
 * Created by Kamil Rajtar on 07.12.17.
 */
data class Board(private val playerPieces: Long, private val opponentPieces: Long) {


	private infix fun Long.nthBit(n: Int) = ((this ushr n) and 1L).toInt()

	fun checkIfCorrect() = (playerPieces and opponentPieces) == 0L

	fun getSite(cell: Int): Site? {
		if (playerPieces nthBit cell == 1)
			return Site.PLAYER
		if (opponentPieces nthBit cell == 1)
			return Site.OPPONENT
		return null
	}


	fun makeMove(cell: Int): Board {
		val (row, column) = getCellCoordinates(cell)
		var flipFlag = 0L
		for (direction in DIRECTIONS) {
			val (directionRow, directionColumn) = direction
			val currentRow = row + directionRow
			val currentColumn = column + directionColumn
			if (!isCorrectCoordinate(currentRow) || !isCorrectCoordinate(currentColumn))
				continue
			val currentCell = getCellNumber(currentRow, currentColumn)
			if (getSite(currentCell) != Site.OPPONENT)
				continue
			if (isPlayersPieceOnTheEnd(currentCell, direction)) {
				flipFlag = flipFlag or getFlipFlag(currentCell, direction)
			}
		}
		val newPlayerPieces = opponentPieces xor flipFlag
		val newOpponentPieces = (playerPieces xor flipFlag) or (1L shl cell)
		return Board(newPlayerPieces, newOpponentPieces)
	}

	private fun getFlipFlag(cell: Int, direction: Pair<Int, Int>): Long {
		var result = 0L
		var currentCell = cell
		val (directionRow, directionColumn) = direction
		while (getSite(currentCell) == Site.OPPONENT) {
			result = result or (1L shl currentCell)
			val (row, column) = getCellCoordinates(currentCell)
			val currentRow = row + directionRow
			val currentColumn = column + directionColumn
			currentCell = getCellNumber(currentRow, currentColumn)
		}
		return result
	}

	fun isCorrectMove(cell: Int): Boolean {
		if (getSite(cell) != null)
			return false
		val (row, column) = getCellCoordinates(cell)
		for (direction in DIRECTIONS) {
			val (directionRow, directionColumn) = direction
			val currentRow = row + directionRow
			val currentColumn = column + directionColumn
			if (!isCorrectCoordinate(currentRow) || !isCorrectCoordinate(currentColumn))
				continue
			val currentCell = getCellNumber(currentRow, currentColumn)
			if (getSite(currentCell) != Site.OPPONENT)
				continue
			if (isPlayersPieceOnTheEnd(currentCell, direction))
				return true
		}
		return false
	}

	private fun isPlayersPieceOnTheEnd(cell: Int, direction: Pair<Int, Int>): Boolean {
		var currentCell = cell
		val (directionRow, directionColumn) = direction
		while (getSite(currentCell) == Site.OPPONENT) {
			val (row, column) = getCellCoordinates(currentCell)
			val currentRow = row + directionRow
			val currentColumn = column + directionColumn
			if (!isCorrectCoordinate(currentRow) || !isCorrectCoordinate(currentColumn))
				return false
			currentCell = getCellNumber(currentRow, currentColumn)
		}
		return getSite(currentCell) == Site.PLAYER
	}


	fun textRepresentation(): String {
		if (!checkIfCorrect())
			throw RuntimeException("Board incorrect.")
		val rowSeparator = " - - - - - - - - \n"
		val result = StringBuilder(rowSeparator)
		for (i in 0..7) {
			for (j in 0..7) {
				result.append('|')
				val cell = getCellNumber(i, j)
				val site = getSite(cell)
				val toAppend = when (site) {
					Site.PLAYER -> 'X'
					Site.OPPONENT -> 'O'
					null -> ' '
				}
				result.append(toAppend)
			}
			result.append("|\n")
			result.append(rowSeparator)
		}
		return result.toString()
	}


	enum class Site {
		PLAYER, OPPONENT
	}


	companion object {
		private const val BOARD_SIZE = 8
		val DIRECTIONS = listOf(
				-1 to -1,//NW
				-1 to 0, //N
				-1 to 1, //NE
				0 to -1, //W
				0 to 1,//E
				1 to -1,//SW
				1 to 0,//S
				1 to 1//SE
		)

		fun getCellNumber(row: Int, column: Int) = row * BOARD_SIZE + column
		fun getCellCoordinates(cell: Int) = cell / 8 to cell % 8
		fun isCorrectCoordinate(coordinate: Int) = coordinate in 0..7

	}

}