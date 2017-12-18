package game

import board.Board
import board.BoardFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import players.AsynchronousPlayerWrapper
import players.Player
import players.ai.FirstAvailableMovePlayer

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
		val black= FirstAvailableMovePlayer("Test 1")
		val white= FirstAvailableMovePlayer("Test 2")
		val boardFactory= BoardFactory()
		val ui:GameUI=object:GameUI {
			override fun printCurrentPlayer(player:Player) {
			}

			override fun displayBoard(board:Board,currentPlayer:Player) {
			}

			override fun markLatestPlayedMove(move:Int) {
			}

			override fun beginGame(black:Player,white:Player) {
			}

			override fun endGame(winner:Player?) {
			}
		}
		val blackAsynchronous=AsynchronousPlayerWrapper(black)
		val whiteAsynchronous=AsynchronousPlayerWrapper(white)
		Game(blackAsynchronous,whiteAsynchronous,boardFactory,ui).playGame()
	}
}