package game

import board.BoardFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import players.ai.FirstAvailableMovePlayer
import java.io.PrintWriter

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
		val output= PrintWriter("/dev/null")
		val black= FirstAvailableMovePlayer("Test 1")
		val white= FirstAvailableMovePlayer("Test 2")
		val boardFactory= BoardFactory()
		Game(black,white,boardFactory,output).playGame()
	}
}