package players

import board.Board

class HumanUIPlayer(override val name: String, private val playerUI: PlayerUI) : AsynchronousPlayer {
    override fun makeMove(board: Board, onMoveDecided: (Int) -> Unit) {
        val correctMoves = (0..63).filter { board.isCorrectMove(it) }
        for (move in correctMoves) {
            playerUI.representMove(move, { onMoveDecided(move) })
        }
    }
}