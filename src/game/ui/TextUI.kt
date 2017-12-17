package game.ui

import board.Board
import players.Player
import java.io.PrintWriter

class TextUI(private val output: PrintWriter, private val black: Player, private val white: Player) : GameUI {
    override fun beginGame(black: Player, white: Player) {
        output.println("Game starts.")
        output.println("Black : ${black.name}.")
        output.println("White : ${white.name}.")
    }

    override fun displayBoard(board: Board, currentPlayer: Player) {
        output.println("Current state of board:")
        board.print(currentPlayer)
    }

    override fun printCurrentPlayer(player: Player) {
        output.println("Now is turn of: ${player.name}")
    }

    override fun endGame(winner: Player?) {
        if (winner == null) output.println("Draw!")
        else output.println("Player ${winner.name} wins!")
    }

    private fun Player.nextPlayer():Player {
        if(this==black)
            return white
        return black
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
}