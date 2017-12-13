package ui.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import ui.components.Cell;

public class MainWindowController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    protected TilePane board;
    @FXML
    protected Label whiteScore;
    @FXML
    protected Label blackScore;
    @FXML
    protected Label currentPlayer;


    public static int BOARD_SIZE = 8;

    public void displayBoard() {
        for(int i=0;i<BOARD_SIZE;i++)
            for(int j=0;j<BOARD_SIZE;j++) {
                Cell cell;
                if(i%2==0) {
                    cell = new Cell(Color.BLACK);
                } else {
                    cell = new Cell(Color.WHITE);
                }
                cell.showDisk();
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
