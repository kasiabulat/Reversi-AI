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

import static javafx.geometry.Pos.CENTER;

/**
 * Created by Kasia on 05.12.2016.
 */
public class Cell extends AnchorPane implements Serializable {
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Button diskButton;

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
	public Cell(Color color) {
		this();
		setColor(color);
	}

	public void showDisk() {
		diskButton.setVisible(true);
	}

	public void flipColor() {
		if(getColor() == Color.BLACK) setColor(Color.WHITE);
		else setColor(Color.BLACK);
	}

	public Color getColor() {
		return (Color) diskButton.getBackground().getFills().get(0).getFill();
	}

	public void setColor(Color color) {
		String styleString = "-fx-background-radius: 5em;";
		if(color == Color.BLACK)
			styleString += "-fx-background-color: black;";
		else styleString += "-fx-background-color: white;";
		diskButton.setStyle(styleString);
	}

	@FXML
	public void initialize() {}
}
