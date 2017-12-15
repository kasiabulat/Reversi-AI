package ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.Serializable;
import java.util.function.BiConsumer;

/**
 * Created by Kasia on 05.12.2016.
 */
public class Cell extends AnchorPane implements Serializable {
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Button diskButton;

	private BiConsumer<Integer,Integer> refreshBoard;
	private int rowId;
	private int columnId;

	private Cell() {
		final FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("cell.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch(final IOException exception) {
			throw new RuntimeException(exception);
		}
		anchorPane.setStyle("-fx-border-color: black;" +
							"-fx-border-width: 2px");
		diskButton.setVisible(false);
	}
	public Cell(String color, BiConsumer<Integer,Integer> refreshBoard, int rowId, int columnId) {
		this();
		setColor(color);
		this.refreshBoard = refreshBoard;
		this.rowId = rowId;
		this.columnId = columnId;
	}

	public void showDisk() {
		diskButton.setVisible(true);
	}

	public Color getColor() {
		return (Color) diskButton.getBackground().getFills().get(0).getFill();
	}

	public void setColor(String colorString) {
		String styleString = "-fx-background-radius: 5em;";
		styleString += "-fx-background-color: "+colorString+";";
		diskButton.setStyle(styleString);
	}

	@FXML
	public void onCellClicked(){
		refreshBoard.accept(rowId,columnId);
	}
	@FXML
	public void initialize() {}
}
