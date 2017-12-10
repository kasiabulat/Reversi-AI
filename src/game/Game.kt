package game

import board.Board
import board.BoardFactory
import players.Player
import java.io.PrintWriter

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class Game(private val black: Player, private val white: Player, private val boardFactory: BoardFactory, private val output: PrintWriter) {


	fun playGame() {
		var board = boardFactory.getStartingBoard()
		output.println("Game starts.")
		output.println("Black : ${black.name}.")
		output.println("White : ${white.name}.")
		var currentPlayer = black
		output.println("Current state of board:")
		board.print(currentPlayer)
		while (!board.isGameEnded()) {
			output.println("Now is turn of: ${currentPlayer.name}")
			if (!board.canPlayerPutPiece()) {
				board.passTurn()
			} else {
				val move = currentPlayer.makeMove(board)
				if (!board.isCorrectMove(move)) {
					throw GameException("Player: ${currentPlayer.name} made incorrect move: $move on ${board.textRepresentation()}")
				}
				board = board.makeMove(move)
			}
			currentPlayer = currentPlayer.nextPlayer()
			output.println("Current state of board:")
			board.print(currentPlayer)
		}
		val resultText = when (board.getDominatingSite()) {
			Board.Site.PLAYER -> "${currentPlayer.name} wins!"
			Board.Site.OPPONENT -> "${currentPlayer.nextPlayer().name} wins!"
			null -> "Draw!"
		}
		output.println(resultText)
	}

	private fun Board.print(currentPlayer: Player) {
		val current = currentPlayer.getSymbol()
		val next = currentPlayer.nextPlayer().getSymbol()
		output.println(textRepresentation(current, next))
	}

	private fun Player.getSymbol(): Char {
		if (this == black)
			return 'X'
		return 'O'
	}

	private fun Player.nextPlayer(): Player {
		if (this == black)
			return white
		return black
	}
}