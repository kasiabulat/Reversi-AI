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
			var exception:Exception?=null

			override fun call():Int {
				try {
					return base.makeMove(board)
				} catch(e:Exception) {
					exception=e;
					throw e
				}
			}

			override fun succeeded() {
				onMoveDecided(value)
			}

			override fun failed() {
				throw RuntimeException("Execution failed",exception)
			}
		}

		Thread(task).start()
	}
}