package players.ai

import board.Board
import players.SynchronousPlayer
import java.util.*

/**
 * Created by Kamil Rajtar on 15.12.17.  */
class RandomizePlayPlayer(override val name:String,private val random:Random,private val numberOfTries:Int):SynchronousPlayer {
	private fun Board.evaluate():Int {
		if(isGameEnded()) {
			return when(getDominatingSite()) {
				Board.Site.PLAYER->1
				Board.Site.OPPONENT->-1
				null->0
			}
		}
		if(!canPlayerPutPiece()) {
			return -passTurn().evaluate()
		}
		val moves=(0..63).filter {isCorrectMove(it)}.toList()
		val elementNumber=random.nextInt(moves.size)
		val playedMove=moves[elementNumber]
		val nextBoard=makeMove(playedMove)
		return -nextBoard.evaluate()
	}

	override fun makeMove(board:Board):Int {

		return (0..63).filter {board.isCorrectMove(it)}.parallelStream().map{board.makeMove(it) to it}.map {
			var result=0
			repeat(numberOfTries)
			{_->
				result+=it.first.evaluate()
			}
			return@map it.second to result
		}.min {A,B-> A.second.compareTo(B.second)}.map {it.first}.get()
	}
}
