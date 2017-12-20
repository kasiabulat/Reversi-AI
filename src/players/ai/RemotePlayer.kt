package players.ai

import board.Board
import players.SynchronousPlayer
import ssh.ISshClient

/**
 * Created by Kamil Rajtar on 20.12.17.  */
class RemotePlayer(override val name:String,private val sshClient:ISshClient,private val command:String):SynchronousPlayer {
	override fun makeMove(board:Board):Int {
		return Integer.valueOf(sshClient.executeCommand("$command ${board.playerPieces} ${board.opponentPieces}"))
	}
}