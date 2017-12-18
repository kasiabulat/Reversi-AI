package players.ai

import board.Board
import board.BoardFactory
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Created by Kamil Rajtar on 10.12.17.  */
internal class FirstAvailableMovePlayerTest {

	private val boardFactory = BoardFactory()
	private val startingBoard = boardFactory.getStartingBoard()

	@Test
	fun makeMove() {
		val player=FirstAvailableMovePlayer("Test 1")
		val expected = Board.getCellNumber(2, 3)
		val actual= player.makeMove(startingBoard)
		assertEquals(expected,actual)
	}
}