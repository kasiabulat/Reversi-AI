package ui.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import ui.components.Cell;

public class MainWindowController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    protected TilePane board;

    public static int BOARD_SIZE = 8;

    public void displayBoard() {
        for(int i=0;i<BOARD_SIZE;i++)
            for(int j=0;j<BOARD_SIZE;j++) {
                Cell cell = new Cell();
                if(i%2==0) cell.showDisk();
                board.getChildren().addAll(cell);
            }
    }

    @FXML
    public void initialize() {
        board.getChildren().clear();
        board.setPadding(new Insets(20, 20, 20, 20));
        board.setPrefColumns(8);
        board.setPrefRows(8);
        board.setMaxSize(380,380);
        board.setStyle("-fx-background-color: forestgreen;");
        displayBoard();
    }
}
