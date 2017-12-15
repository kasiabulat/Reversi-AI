package players

import board.Board

class HumanUIPlayer(override val name: String) : Player {
    override fun makeMove(board: Board, onMoveDecided: (Int) -> Unit) {}
}