import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.FileOutputStream
import java.io.PrintWriter
import java.util.*
import kotlin.test.assertEquals

/**
 * Created by Kamil Rajtar on 08.12.17.  */
internal class HumanPlayerTest {

	private val boardFactory = BoardFactory()
	private val startingBoard = boardFactory.getStartingBoard()


	@Test
	fun makeMove() {
		val playerMoves = "3\n 4\n"
		val inputStream = ByteArrayInputStream(playerMoves.toByteArray())
		val outputStream = FileOutputStream("/dev/null")
		val scanner = Scanner(inputStream)
		val printWriter = PrintWriter(outputStream)
		val player = HumanPlayer(scanner, printWriter)

		val expected = Board.getCellNumber(2, 3)
		val actual = player.makeMove(startingBoard)

		assertEquals(expected, actual)
	}
}