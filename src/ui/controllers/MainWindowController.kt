package ui.controllers

import board.Board
import board.Board.Site
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

	private fun getCurrentSite(currentPlayer:Player):Site {
		return if(currentPlayer==black) Site.PLAYER else Site.OPPONENT
	}

	private fun initialiseCells() {
		cells.clear()
		for(i in 0 until Board.BOARD_SIZE)
			for(j in 0 until Board.BOARD_SIZE) {
				val cell=Cell(i,j)
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
		val dialogVbox=VBox(20.0)

		val gameEndMessage:String
		if(winner==null)
			gameEndMessage="Draw!"
		else
			gameEndMessage="Player "+winner.name+" wins!"

		val message=Label(gameEndMessage)
		message.alignment=Pos.CENTER
		dialogVbox.children.add(message)
		dialog.scene=Scene(dialogVbox,260.0,40.0)
		dialog.show()
	}

	override fun printCurrentPlayer(player:Player) {
		currentPlayerLabel.text=player.name
	}

	override fun displayBoard(board:Board,currentPlayer:Player) {
		for(i in 0 until Board.BOARD_SIZE)
			for(j in 0 until Board.BOARD_SIZE) {
				val site=board.getSite(i*Board.BOARD_SIZE+j)
				val cell=cells[i*Board.BOARD_SIZE+j]
				if(site!=null) {
					val color=if((currentPlayer==black&&site==Board.Site.PLAYER)||(currentPlayer==white&&site==Board.Site.OPPONENT))
						"black"
					else
						"white"
					cell.setColor(color)
				}
				cell.setDiskVisible(site!=null)
			}

		blackScore.text=board.getScore(Site.PLAYER).toString()
		whiteScore.text=board.getScore(Site.OPPONENT).toString()
	}

	override fun beginGame(black:Player,white:Player) {
		this.black=black
		this.white=white
	}

	override fun representMove(move:Int,onDecided:Function0<Unit>) {
		val cell=cells[move]
		cell.setColor("grey")
		cell.setOnClick(onDecided)
		cell.setDiskVisible(true)
	}
}
