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

	public Cell() {
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
		diskButton.setStyle("-fx-background-color: white; " +
							"-fx-background-radius: 5em;");
		diskButton.setVisible(false);
	}

	public void showDisk() {
		diskButton.setVisible(true);
	}

	@FXML
	public void initialize() {}
}
