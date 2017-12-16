package game.ui

import board.Board
import players.Player

interface GameUI {
    fun endGame(winner : Player?)
    fun printCurrentPlayer(player: Player)
    fun displayBoard(board: Board, currentPlayer: Player)
    fun nextPlayer(currentPlayer : Player): Player
    fun beginGame(black: Player, white: Player)
}