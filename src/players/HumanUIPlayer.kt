package players

import board.Board

class HumanUIPlayer(override val name: String) : Player {
    lateinit var playerUI: PlayerUI

    fun setUI(playerUI: PlayerUI) {
        this.playerUI = playerUI
    }

    override fun makeMove(board: Board, onMoveDecided: (Int) -> Unit) {
        val correctMoves = (0..63).filter { board.isCorrectMove(it) }
        for (move in correctMoves) {
            playerUI.representMove(move, { onMoveDecided(move) })
        }
    }
}