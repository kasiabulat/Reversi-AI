package ui.controllers

import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.TilePane
import javafx.scene.paint.Color
import ui.components.Cell

class MainWindowController {
	@FXML
	private val anchorPane: AnchorPane? = null
	@FXML
	protected var board: TilePane? = null
	@FXML
	protected var whiteScore: Label? = null
	@FXML
	protected var blackScore: Label? = null
	@FXML
	protected var currentPlayer: Label? = null

	fun displayBoard() {
		for (i in 0 until BOARD_SIZE)
			for (j in 0 until BOARD_SIZE) {
				val cell: Cell
				if (i % 2 == 0) {
					cell = Cell(Color.BLACK)
				} else {
					cell = Cell(Color.WHITE)
				}
				cell.showDisk()
				board!!.children.addAll(cell)
			}
	}

	@FXML
	fun initialize() {
		board!!.children.clear()
		board!!.padding = Insets(20.0, 20.0, 20.0, 20.0)
		board!!.prefColumns = 8
		board!!.prefRows = 8
		board!!.setMaxSize(380.0, 380.0)
		board!!.style = "-fx-background-color: forestgreen;"
		displayBoard()
	}

	companion object {


		var BOARD_SIZE = 8
	}
}
