package game

import board.Board
import players.Player

interface GameUI {
    fun endGame(winner :Player?)
    fun printCurrentPlayer(player: Player)
    fun displayBoard(board: Board, currentPlayer: Player)
    fun markLatestPlayedMove(move:Int)
    fun beginGame(black: Player, white: Player)
}