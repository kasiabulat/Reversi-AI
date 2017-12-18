package players.ai

import board.Board
import players.SynchronousPlayer

/**
 * Created by Kamil Rajtar on 18.12.17.
 */
class HybridPlayer(override val name:String,
				   private val sparsePlayer:SynchronousPlayer,
				   private val densePlayer:SynchronousPlayer,
				   private val threshold:Int):SynchronousPlayer {

	override fun makeMove(board:Board):Int {
		if(board.emptyFields>threshold)
			return sparsePlayer.makeMove(board)
		return densePlayer.makeMove(board)
	}
}