package game

import board.BoardFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import players.HumanPlayer
import java.io.PrintWriter
import java.util.*

/**
 * Created by Kamil Rajtar on 10.12.17.  */
internal class GameTest {

	@BeforeEach
	fun setUp() {
	}

	@AfterEach
	fun tearDown() {
	}

	@Test
	fun playGame() {
		val input= Scanner(System.`in`)
		val output= PrintWriter(System.out,true)
		val black= HumanPlayer("Player 1",input,output)
		val white= HumanPlayer("Player 2",input,output)
		val boardFactory= BoardFactory()
		Game(black,white,boardFactory,output).playGame()
	}
}