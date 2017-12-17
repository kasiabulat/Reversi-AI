package ui.components

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color

import java.io.IOException
import java.io.Serializable

/**
 * Created by Kasia on 05.12.2016.
 */
class Cell(private val rowId:Int,private val columnId:Int):AnchorPane(),Serializable {
	@FXML private lateinit var anchorPane:AnchorPane
	@FXML private lateinit var diskButton:Button

	private var onClick:(() -> Unit)?=null

	val color:Color
		get()=diskButton.background.fills[0].fill as Color

	init {
		val fxmlLoader=FXMLLoader(javaClass.getResource("cell.fxml"))
		fxmlLoader.setRoot(this)
		fxmlLoader.setController(this)

		try {
			fxmlLoader.load<Any>()
		} catch(exception:IOException) {
			throw RuntimeException(exception)
		}

		anchorPane.style="-fx-border-color: black;"+"-fx-border-width: 2px"
		diskButton.isVisible=false
	}


	fun setDiskVisible(visible:Boolean)
	{
		diskButton.isVisible=visible
	}


	fun setColor(colorString:String) {
		var styleString="-fx-background-radius: 5em;"+"-fx-text-fill: red;"
		styleString+="-fx-background-color: $colorString;"
		diskButton.style=styleString
	}

	fun setOnClick(onClick:(()->Unit)?) {
		this.onClick=onClick
	}

	fun setText(text:String) {
		diskButton.text=text
	}

	@FXML
	fun onCellClicked() {
		onClick?.invoke()
	}

	@FXML
	fun initialize() {
	}
}
