package ui.windows

import board.BoardFactory
import game.Game
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import players.AsynchronousPlayerWrapper
import players.HumanUIPlayer
import players.ai.CheckAllOptionsPlayer
import players.ai.FirstAvailableMovePlayer
import players.ai.RandomizePlayPlayer
import players.ai.RemotePlayer
import ssh.SshClient
import ui.controllers.MainWindowController
import java.util.*

class Main:Application() {

    @Throws(Exception::class)
    override fun start(primaryStage:Stage) {
        val fxmlLoader=FXMLLoader(javaClass.getResource("main_window.fxml"))
        val mainWindowController=MainWindowController()

        val black=RandomizePlayPlayer("AI",Random(),10000)
        val blackAsynchronous=AsynchronousPlayerWrapper(black)
        val whiteAsynchronous=HumanUIPlayer("Player",mainWindowController)
        val game=Game(blackAsynchronous,whiteAsynchronous,BoardFactory(),mainWindowController)

        mainWindowController.setGame(game)

        fxmlLoader.setController(mainWindowController)
        fxmlLoader.load<Any>()

        primaryStage.title="Reversi"
        primaryStage.scene=Scene(fxmlLoader.getRoot<Parent>(),500.0,400.0)
        primaryStage.isResizable=false
        primaryStage.show()
    }

}


