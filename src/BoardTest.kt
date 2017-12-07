import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Created by Kamil Rajtar on 07.12.17.  */
internal class BoardTest {

	private val startingBoard = BoardFactory().getBoard(
			listOf( 4 to 3, 3 to 4),
			listOf( 3 to 3, 4 to 4))


	@BeforeEach
	fun setUp() {
	}

	@AfterEach
	fun tearDown() {
	}

	@Test
	fun getBitNumber() {
		assertEquals(0, Board.getCellNumber(0, 0))
		assertEquals(5, Board.getCellNumber(0, 5))
		assertEquals(8, Board.getCellNumber(1, 0))
		assertEquals(63, Board.getCellNumber(7, 7))
	}

	@Test
	fun getSite() {
		val board = Board(1, 8)
		assertEquals(Board.Site.PLAYER, board.getSite(0))
		assertEquals(Board.Site.OPPONENT, board.getSite(3))
		assertNull(board.getSite(13))
	}

	@Test
	fun isCorrectMove() {
		val correctMoves = listOf(2 to 3, 3 to 2, 5 to 4, 4 to 5)
		for (i in 0..7)
			for (j in 0..7) {
				val cell = Board.getCellNumber(i, j)
				assertEquals(correctMoves.contains(i to j), startingBoard.isCorrectMove(cell))
			}
	}

	@Test
	fun textRepresentation() {

		val expected = " - - - - - - - - \n" +
				"| | | | | | | | |\n" +
				" - - - - - - - - \n" +
				"| | | | | | | | |\n" +
				" - - - - - - - - \n" +
				"| | | | | | | | |\n" +
				" - - - - - - - - \n" +
				"| | | |O|X| | | |\n" +
				" - - - - - - - - \n" +
				"| | | |X|O| | | |\n" +
				" - - - - - - - - \n" +
				"| | | | | | | | |\n" +
				" - - - - - - - - \n" +
				"| | | | | | | | |\n" +
				" - - - - - - - - \n" +
				"| | | | | | | | |\n" +
				" - - - - - - - - \n"
		val actual=startingBoard.textRepresentation()
		assertEquals(expected,actual)
	}

}