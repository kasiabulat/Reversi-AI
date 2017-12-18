package players

import board.Board
import javafx.concurrent.Task

/**
 * Created by Kamil Rajtar on 18.12.17.
 */
class AsynchronousPlayerWrapper(val base:SynchronousPlayer):AsynchronousPlayer {
	override val name:String
		get()=base.name

	override fun makeMove(board:Board,onMoveDecided:(Int)->Unit) {
		val task=object:Task<Int>() {
			override fun call():Int {
				return base.makeMove(board)
			}

			override fun succeeded() {
				onMoveDecided(value)
			}
		}

		Thread(task).start()
	}
}