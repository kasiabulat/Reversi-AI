package ssh

/**
 * Created by Kamil Rajtar on 20.12.17.
 */
interface ISshClient:AutoCloseable {
	fun executeCommand(command:String):String
}