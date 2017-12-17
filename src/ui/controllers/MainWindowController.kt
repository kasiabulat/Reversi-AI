package ui.controllers

import board.Board
import game.Game
import game.ui.GameUI
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.TilePane
import javafx.scene.layout.VBox
import javafx.stage.Modality
import javafx.stage.Stage
import players.Player
import players.PlayerUI
import ui.components.Cell
import java.util.*

class MainWindowController:GameUI,PlayerUI {


	@FXML private lateinit var anchorPane:AnchorPane
	@FXML private lateinit var tilePane:TilePane
	@FXML private lateinit var whiteScore:Label
	@FXML private lateinit var blackScore:Label
	@FXML private lateinit var currentPlayerLabel:Label
	private lateinit var black:Player
	private lateinit var white:Player
	private lateinit var game:Game
	private val cells=ArrayList<Cell>()

	fun setGame(game:Game) {
		this.game=game
	}

	private fun initialiseCells() {
		cells.clear()
		for(i in 0 until Board.BOARD_SIZE)
			for(j in 0 until Board.BOARD_SIZE) {
				val cell=Cell()
				cell.setDiskVisible(false)
				cells.add(cell)
				tilePane.children.addAll(cell)
			}
	}

	@FXML
	fun initialize() {
		tilePane.children.clear()
		tilePane.padding=Insets(20.0,20.0,20.0,20.0)
		tilePane.prefColumns=8
		tilePane.prefRows=8
		tilePane.setMaxSize(380.0,380.0)
		tilePane.style="-fx-background-color: forestgreen;"
		initialiseCells()
		game.playGame()
	}

	override fun endGame(winner:Player?) {
		val primaryStage=anchorPane.scene.window as Stage
		val dialog=Stage()
		dialog.initModality(Modality.APPLICATION_MODAL)
		dialog.initOwner(primaryStage)
		dialog.isResizable=false
		val dialogVBox=VBox(20.0)

		val gameEndMessage=if(winner==null)
			"Draw!"
		else
			"Player "+winner.name+" wins!"

		val message=Label(gameEndMessage)
		message.alignment=Pos.CENTER
		dialogVBox.children.add(message)
		dialog.scene=Scene(dialogVBox,260.0,40.0)
		dialog.show()
	}

	override fun printCurrentPlayer(player:Player) {
		currentPlayerLabel.text=player.name
	}

	override fun displayBoard(board:Board,currentPlayer:Player) {

		val blackSite=if(currentPlayer==black) Board.Site.PLAYER else Board.Site.OPPONENT
		val whiteSite=if(currentPlayer==white) Board.Site.PLAYER else Board.Site.OPPONENT

		for(i in 0 until Board.BOARD_SIZE)
			for(j in 0 until Board.BOARD_SIZE) {
				val site=board.getSite(i*Board.BOARD_SIZE+j)
				val cell=cells[i*Board.BOARD_SIZE+j]
				if(site!=null) {
					val color=if(site==blackSite)
						"black"
					else
						"white"
					cell.setColor(color)
				}
				cell.setDiskVisible(site!=null)
				cell.setOnClick(null)
				cell.setText("")
			}



		blackScore.text=board.getScore(blackSite).toString()
		whiteScore.text=board.getScore(whiteSite).toString()
	}

	override fun markLatestPlayedMove(move:Int) {
		val cell=cells[move]
		cell.setText("I")
	}

	override fun beginGame(black:Player,white:Player) {
		this.black=black
		this.white=white
	}

	override fun representMove(move:Int,onDecided:() -> Unit) {
		val cell=cells[move]
		cell.setColor("grey")
		cell.setOnClick(onDecided)
		cell.setDiskVisible(true)
	}
}
