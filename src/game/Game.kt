package game

import board.Board
import board.BoardFactory
import players.AsynchronousPlayer

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class Game(private val black:AsynchronousPlayer,private val white:AsynchronousPlayer,private val boardFactory:BoardFactory,private val ui:GameUI) {

	private fun endGame(board:Board,currentAsynchronousPlayer:AsynchronousPlayer) {
		val winner=when(board.getDominatingSite()) {
			Board.Site.PLAYER->currentAsynchronousPlayer
			Board.Site.OPPONENT->currentAsynchronousPlayer.nextPlayer()
			null->null
		}
		ui.endGame(winner)
	}

	private fun AsynchronousPlayer.nextPlayer():AsynchronousPlayer {
		if(this==black)
			return white
		return black
	}

	private fun evaluate(board:Board,player:AsynchronousPlayer) {
		if(board.isGameEnded()) {
			endGame(board,player)
			return
		}
		var currentBoard=board
		var currentAsynchronousPlayer=player
		if(!currentBoard.canPlayerPutPiece()) {
			currentBoard=currentBoard.passTurn()
			currentAsynchronousPlayer=currentAsynchronousPlayer.nextPlayer()
		}

		ui.printCurrentPlayer(currentAsynchronousPlayer)

		currentAsynchronousPlayer.makeMove(currentBoard,{move->
			if(!currentBoard.isCorrectMove(move)) {
				throw GameException("AsynchronousPlayer: ${currentAsynchronousPlayer.name} made incorrect move: $move on ${currentBoard.textRepresentation()}")
			}
			currentBoard=currentBoard.makeMove(move)
			ui.displayBoard(currentBoard,currentAsynchronousPlayer.nextPlayer())
			ui.markLatestPlayedMove(move)
			evaluate(currentBoard,currentAsynchronousPlayer.nextPlayer())
		})
	}

	fun playGame() {
		val board=boardFactory.getStartingBoard()
		ui.beginGame(black,white)
		val currentAsynchronousPlayer=black
		ui.displayBoard(board,currentAsynchronousPlayer)
		evaluate(board,currentAsynchronousPlayer)
	}
}