package ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.beans.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static javafx.geometry.Pos.CENTER;

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
		//if(color == Color.BLACK)
		//	diskButton.setBackground(new Background(new BackgroundFill(color,new CornerRadii(5),new Insets(0))));
			//styleString += "-fx-background-color: black;";
		//else //styleString += "-fx-background-color: white;";

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
