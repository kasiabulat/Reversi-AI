package board

/**
 * Created by Kamil Rajtar on 07.12.17.
 */
class BoardFactory {

	private fun getState(cells: Collection<Pair<Int, Int>>): Long {
		var result = 0L
		for ((row, column) in cells)
			result = result or (1L shl Board.getCellNumber(row, column))
		return result
	}

	fun getBoard(playerCollection: Collection<Pair<Int, Int>>, opponentCollection: Collection<Pair<Int, Int>>): Board {
		val playerPieces = getState(playerCollection)
		val opponentPieces = getState(opponentCollection)

		val result = Board(playerPieces, opponentPieces)
		return result
	}

	fun getStartingBoard(): Board {
		return getBoard(
				listOf(4 to 3, 3 to 4),
				listOf(3 to 3, 4 to 4))
	}
}