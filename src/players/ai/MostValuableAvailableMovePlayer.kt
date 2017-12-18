package players.ai

import board.Board
import players.SynchronousPlayer
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Kamil Rajtar on 10.12.17.
 */
class MostValuableAvailableMovePlayer(override val name:String):SynchronousPlayer {


	override fun makeMove(board:Board):Int {
		return (0..63)
				.filter {board.isCorrectMove(it)}
				.map {board.getMoveValue(it) to it}
				.maxBy {it.first}
				?.second ?: throw RuntimeException("Cannot make move")
//		var bestValue = 0
//		var bestMove = -1
//		for(move in moves) {
//			val value = board.getMoveValue(move)
//			if(value > bestValue) {
//				bestMove = move
//				bestValue = value
//			}
//		}
	}
}