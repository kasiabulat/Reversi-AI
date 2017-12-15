package board

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.*

/**
 * Created by Kamil Rajtar on 07.12.17.  */
internal class BoardTest {

	private val boardFactory = BoardFactory()
	private val startingBoard = boardFactory.getStartingBoard()


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
		val board = Board(0b1, 0b1000)
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
				assertEquals(correctMoves.contains(i to j), startingBoard.isCorrectMove(cell), "At ($i,$j)")
			}

		for (i in listOf(-1, 8))//Moves not in tilePane
			for (j in listOf(-1, 8)) {
				val cell = Board.getCellNumber(i, j)
				assertFalse(startingBoard.isCorrectMove(cell), "Not in tilePane fail ($i,$j)")
			}
	}

	@Test
	fun canPlayerPutPiece() {
		val startingBoard = boardFactory.getStartingBoard()
		assertTrue { startingBoard.canPlayerPutPiece() }
		val completedBoard = Board(0b111, 0)
		assertFalse { completedBoard.canPlayerPutPiece() }
	}

	@Test
	fun makeMove() {
		val expected = boardFactory.getBoard(listOf(4 to 4), listOf(2 to 3, 3 to 3, 4 to 3, 3 to 4))
		val actual = startingBoard.makeMove(Board.getCellNumber(2, 3))
		assertEquals(expected, actual, "Expected:\n" + expected.textRepresentation() + "Actual:\n" + actual.textRepresentation())
	}

	@Test
	fun passTurn() {
		val completedBoard = Board(0b111, 0b0)
		val expected = Board(0b0, 0b111)
		val actual = completedBoard.passTurn()
		assertEquals(expected, actual)
	}

	@Test
	fun passTurnFail() {
		assertFailsWith(BoardException::class) {
			startingBoard.passTurn()
		}
	}

	@Test
	fun getDominatingSite() {
		val playerMore = Board(0b111, 0b1000)
		val opponentMore = Board(0b100, 0b1011)
		val equalBoard = Board(0b111, 0b111000)
		assertEquals(Board.Site.PLAYER, playerMore.getDominatingSite())
		assertEquals(Board.Site.OPPONENT, opponentMore.getDominatingSite())
		assertNull(equalBoard.getDominatingSite())
	}

	@Test
	fun textRepresentation() {
		val expected = "  1 2 3 4 5 6 7 8 \n" +
				"  - - - - - - - - \n" +
				"1| | | | | | | | |\n" +
				"  - - - - - - - - \n" +
				"2| | | | | | | | |\n" +
				"  - - - - - - - - \n" +
				"3| | | |.| | | | |\n" +
				"  - - - - - - - - \n" +
				"4| | |.|O|X| | | |\n" +
				"  - - - - - - - - \n" +
				"5| | | |X|O|.| | |\n" +
				"  - - - - - - - - \n" +
				"6| | | | |.| | | |\n" +
				"  - - - - - - - - \n" +
				"7| | | | | | | | |\n" +
				"  - - - - - - - - \n" +
				"8| | | | | | | | |\n" +
				"  - - - - - - - - \n"
		val actual = startingBoard.textRepresentation()
		assertEquals(expected, actual)
	}

	@Test()
	fun testIncorrectBoard() {
		assertFailsWith(BoardException::class) {
			Board(0b010, 0b011)
		}
	}

	@Test
	fun testIsGameEnded() {
		assertFalse(startingBoard.isGameEnded())
		val endedGame = Board(0b000, 0b111)
		assertTrue(endedGame.isGameEnded())
	}

	@Test
	fun testGetScore() {
		val board=Board(0b11010,0b00101)
		val actualPlayer= board.getScore(Board.Site.PLAYER)
		val actualOpponent=board.getScore(Board.Site.OPPONENT)
		val expectedPlayer=3
		val expectedOpponent=2
		assertEquals(expectedPlayer,actualPlayer)
		assertEquals(expectedOpponent,actualOpponent)
	}

}