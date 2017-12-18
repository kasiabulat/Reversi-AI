package players.ai

import board.Board
import players.SynchronousPlayer
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Kamil Rajtar on 18.12.17.  */
class CheckAllOptionsPlayer(override val name:String,private val maxDepth:Int=64,private val numberOfTries:Int=1):SynchronousPlayer {

	private val results=ConcurrentHashMap<Board,Int>()
	private val random=Random()
	private fun Board.evaluateRandom():Int {
		if(isGameEnded()) {
			return when(getDominatingSite()) {
				Board.Site.PLAYER->1
				Board.Site.OPPONENT->-1
				null->0
			}
		}
		if(!canPlayerPutPiece()) {
			return -passTurn().evaluateRandom()
		}
		val moves=(0..63).filter {isCorrectMove(it)}.toList()
		val elementNumber=random.nextInt(moves.size)
		val playedMove=moves[elementNumber]
		val nextBoard=makeMove(playedMove)
		return -nextBoard.evaluateRandom()
	}

	private fun Board.evaluate(depth:Int):Int {
		val previousResult=results[this]
		if(previousResult!=null)
			return previousResult
		if(isGameEnded()) {
			return when(getDominatingSite()) {
				Board.Site.PLAYER->numberOfTries
				Board.Site.OPPONENT->-numberOfTries
				null->0
			}
		}

		if(!canPlayerPutPiece()) {
			return -passTurn().evaluate(depth)
		}
		if(depth>maxDepth)
		{
			return (0..63).filter {isCorrectMove(it)}.parallelStream().map{makeMove(it) to it}.map {
				var result=0
				repeat(numberOfTries)
				{_->
					result+=it.first.evaluateRandom()
				}
				return@map it.second to result
			}.min {A,B-> A.second.compareTo(B.second)}.map {it.first}.get()

		}


		val result=-((0..63).filter {isCorrectMove(it)}.map {makeMove(it).evaluate(depth+1)}.min() ?: throw RuntimeException("Cannot make move"))
		results[this]=result
		return result
	}

	override fun makeMove(board:Board):Int {
		val result=(0..63).filter {board.isCorrectMove(it)}.parallelStream().map {board.makeMove(it).evaluate(0) to it}.min {a,b-> a.first.compareTo(b.first)}.get().second
		results.clear()
		return result
	}
}