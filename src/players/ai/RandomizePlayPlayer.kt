package players.ai

import board.Board
import players.SynchronousPlayer
import java.util.*

/**
 * Created by Kamil Rajtar on 15.12.17.  */
class RandomizePlayPlayer(override val name:String,private val random:Random,private val numberOfTries:Int):SynchronousPlayer {
	private fun evaluateBoard(board:Board):Int {
		if(board.isGameEnded()) {
			return when(board.getDominatingSite()) {
				Board.Site.PLAYER->1
				Board.Site.OPPONENT->-1
				null->0
			}
		}
		if(!board.canPlayerPutPiece()) {
			return -evaluateBoard(board.passTurn())
		}
		val moves=(0..63).filter {board.isCorrectMove(it)}.toList()
		val elementNumber=random.nextInt(moves.size)
		val playedMove=moves[elementNumber]
		val nextBoard=board.makeMove(playedMove)
		return -(evaluateBoard(nextBoard))
	}

	override fun makeMove(board:Board):Int {

		return (0..63).filter {board.isCorrectMove(it)}.parallelStream().map {
			var result=0
			val currentBoard=board.makeMove(it)
			repeat(numberOfTries)
			{
				result+=evaluateBoard(currentBoard)
			}
			return@map it to result
		}.min {A,B-> A.second.compareTo(B.second)}.map {it.first}.get()
	}
}
