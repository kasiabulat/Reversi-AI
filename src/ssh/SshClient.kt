package ssh

import com.jcraft.jsch.ChannelExec
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import java.util.*

/**
 * Created by Kamil Rajtar on 20.12.17.  */
class SshClient(host:String,
				port:Int,
				login:String,
				timeout:Int=0):ISshClient {

	private val session:Session

	init {
		val jsch=JSch()
		jsch.addIdentity("~/.ssh/id_rsa")
		session=jsch.getSession(login,host,port)
		session.timeout=timeout
		session.setConfig(STRICT_HOST_KEY_CHECKING_KEY,
				STRICT_HOST_KEY_CHECKING_VALUE)

		session.connect()
	}

	override fun executeCommand(command:String):String {
		val channel=session.openChannel(CHANNEL_TYPE_EXEC) as ChannelExec
		channel.setCommand(command)

		channel.connect()

		val scanner=Scanner(channel.inputStream)
		val scannerError=Scanner(channel.errStream)
		val result=StringBuilder()
		while(scanner.hasNextLine())
			result.appendln(scanner.nextLine())
		while(scannerError.hasNextLine())
			System.err.println(scannerError.nextLine())


		channel.disconnect()

		val resultText=result.toString()

		return resultText
	}

	override fun close() {
		session.disconnect()
	}

	companion object {
		private const val STRICT_HOST_KEY_CHECKING_KEY="StrictHostKeyChecking"
		private const val STRICT_HOST_KEY_CHECKING_VALUE="no"
		private const val CHANNEL_TYPE_EXEC="exec"
	}
}
