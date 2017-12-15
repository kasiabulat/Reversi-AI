package ui.controllers;

import board.Board;
import board.Board.Site;
import board.BoardFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import players.Player;
import ui.components.Cell;

public class MainWindowController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    protected TilePane tilePane;
    @FXML
    protected Label whiteScore;
    @FXML
    protected Label blackScore;
    @FXML
    protected Label currentPlayerLabel;

    private Board board;

    public static int BOARD_SIZE = 8;
    private Player player1;
    private Player player2;

    private MainWindowController() {
        super();
    }
    public MainWindowController(Player player1, Player player2) {
        this();
        this.player1 = player1;
        this.player2 = player2;
    }

    private void addCell(int rowId, int columnId) {
        int cell_id = rowId * BOARD_SIZE + columnId;
        if(board.isCorrectMove(cell_id))
            board = board.makeMove(cell_id);
        displayBoard();
    }

    public void displayBoard() {
        System.out.println("displaying board");
        tilePane.getChildren().clear();
        for(int i=0;i<BOARD_SIZE;i++)
            for(int j=0;j<BOARD_SIZE;j++) {
                Site site = board.getSite(i * BOARD_SIZE + j);
                Cell cell;
                if(site != null) {
                    String color = (site.equals(Site.PLAYER)) ? "black" : "white";
                    cell = new Cell(color, this::addCell,i,j);
                    cell.showDisk();

                } else {
                    cell = new Cell("grey", this::addCell,i,j);
                    if(board.isCorrectMove(i * BOARD_SIZE + j))
                        cell.showDisk();
                }
                tilePane.getChildren().addAll(cell);
            }
    }

    @FXML
    public void initialize() {
        tilePane.getChildren().clear();
        tilePane.setPadding(new Insets(20, 20, 20, 20));
        tilePane.setPrefColumns(8);
        tilePane.setPrefRows(8);
        tilePane.setMaxSize(380,380);
        tilePane.setStyle("-fx-background-color: forestgreen;");

        BoardFactory boardFactory = new BoardFactory();
        board = boardFactory.getStartingBoard();
        displayBoard();
    }
}
