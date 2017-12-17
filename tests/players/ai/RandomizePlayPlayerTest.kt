package players.ai

import board.BoardFactory
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertTrue

/**
 * Created by Kamil Rajtar on 15.12.17.  */
class RandomizePlayPlayerTest {

	private val boardFactory = BoardFactory()
	private val startingBoard = boardFactory.getStartingBoard()


	@Test
	fun makeMove() {
		val player = RandomizePlayPlayer("Randomised", Random(123),1)
		player.makeMove(startingBoard, {
			assertTrue(startingBoard.isCorrectMove(it))
		})
	}
}