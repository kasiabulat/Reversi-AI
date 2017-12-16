package ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

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

	private Function0<Unit> onClick;
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

	public Cell(int rowId, int columnId) {
		this();
		this.rowId = rowId;
		this.columnId = columnId;
	}

	public void showDisk() {
		diskButton.setVisible(true);
	}
	public void hideDisk() {
		diskButton.setVisible(false);
	}

	public Color getColor() {
		return (Color) diskButton.getBackground().getFills().get(0).getFill();
	}

	public void setColor(String colorString) {
		String styleString = "-fx-background-radius: 5em;" +  "-fx-text-fill: red;";
		styleString += "-fx-background-color: "+colorString+";";
		diskButton.setStyle(styleString);
	}

	public void setOnClick(Function0<Unit> onClick) {
		this.onClick = onClick;
	}

	public void setText(String text) {
		diskButton.setText(text);
	}

	@FXML
	public void onCellClicked(){
		onClick.invoke();
	}
	@FXML
	public void initialize() {}
}
