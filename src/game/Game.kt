package game

import board.Board
import board.BoardFactory
import game.ui.GameUI
import players.Player

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class Game(private val black:Player,private val white:Player,private val boardFactory:BoardFactory,private val ui:GameUI) {

	private fun endGame(board:Board,currentPlayer:Player) {
		val winner=when(board.getDominatingSite()) {
			Board.Site.PLAYER->currentPlayer
			Board.Site.OPPONENT->currentPlayer.nextPlayer()
			null->null
		}
		ui.endGame(winner)
	}

	private fun Player.nextPlayer():Player {
		if(this==black)
			return white
		return black
	}

	private fun evaluate(board:Board,player:Player) {
		if(board.isGameEnded()) {
			endGame(board,player)
			return
		}
		var currentBoard=board
		var currentPlayer=player
		if(!currentBoard.canPlayerPutPiece()) {
			currentBoard=currentBoard.passTurn()
			currentPlayer=currentPlayer.nextPlayer()
		}

		ui.printCurrentPlayer(currentPlayer)

		currentPlayer.makeMove(currentBoard,{move->
			if(!currentBoard.isCorrectMove(move)) {
				throw GameException("Player: ${currentPlayer.name} made incorrect move: $move on ${currentBoard.textRepresentation()}")
			}
			currentBoard=currentBoard.makeMove(move)
			ui.displayBoard(currentBoard,currentPlayer.nextPlayer())
			evaluate(currentBoard,currentPlayer.nextPlayer())
		})
	}

	fun playGame() {
		val board=boardFactory.getStartingBoard()
		ui.beginGame(black,white)
		val currentPlayer=black
		ui.displayBoard(board,currentPlayer)
		evaluate(board,currentPlayer)
	}
}