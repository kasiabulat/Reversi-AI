package players.ai

import board.Board
import players.SynchronousPlayer
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Kamil Rajtar on 18.12.17.  */
class CheckAllOptionsPlayer(override val name:String,private val depth:Int):SynchronousPlayer {

	private val results=ConcurrentHashMap<Board,Int>()

	private fun Board.evaluate():Int {
		val previousResult=results[this]
		if(previousResult!=null)
			return previousResult
		System.out.print(" ")
		System.out.print(emptyFields)
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

		val result=-((0..63).filter {isCorrectMove(it)}.map {makeMove(it).evaluate()}.min() ?: throw RuntimeException("Cannot make move"))
		results[this]=result
		return result
	}

	override fun makeMove(board:Board):Int {
		return (0..63).filter {board.isCorrectMove(it)}.parallelStream().map {board.makeMove(it).evaluate() to it}.min {a,b-> a.first.compareTo(b.first)}.get().second
	}
}