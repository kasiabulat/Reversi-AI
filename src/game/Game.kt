package game

import board.Board
import board.BoardFactory
import players.Player
import java.io.PrintWriter

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class Game(private val black: Player, private val white: Player, private val boardFactory: BoardFactory, private val output: PrintWriter) {

	private fun endGame(board: Board, currentPlayer: Player) {
		val resultText = when (board.getDominatingSite()) {
			Board.Site.PLAYER -> "${currentPlayer.name} wins!"
			Board.Site.OPPONENT -> "${currentPlayer.nextPlayer().name} wins!"
			null -> "Draw!"
		}
		output.println(resultText)
	}

	private fun evaluate(board: Board, player: Player) {
		if (board.isGameEnded()) {
			endGame(board, player)
			return
		}
		var currentBoard = board
		var currentPlayer = player
		if (!currentBoard.canPlayerPutPiece()) {
			currentBoard = currentBoard.passTurn()
			currentPlayer = currentPlayer.nextPlayer()
		}
		output.println("Now is turn of: ${currentPlayer.name}")

		currentPlayer.makeMove(currentBoard, { move ->
			if (!currentBoard.isCorrectMove(move)) {
				throw GameException("Player: ${currentPlayer.name} made incorrect move: $move on ${currentBoard.textRepresentation()}")
			}
			currentBoard = currentBoard.makeMove(move)

			output.println("Current state of board:")
			currentBoard.print(currentPlayer)
			evaluate(currentBoard, currentPlayer.nextPlayer())
		})
	}

	fun playGame() {
		val board = boardFactory.getStartingBoard()
		output.println("Game starts.")
		output.println("Black : ${black.name}.")
		output.println("White : ${white.name}.")
		val currentPlayer = black
		output.println("Current state of board:")
		board.print(currentPlayer)
		evaluate(board, currentPlayer)
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