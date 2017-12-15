package ui.controllers;

import board.Board;
import board.Board.Site;
import board.BoardFactory;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import players.Player;
import ui.components.Cell;

public class MainWindowController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TilePane tilePane;
    @FXML
    protected Label whiteScore;
    @FXML
    protected Label blackScore;
    @FXML
    protected Label currentPlayerLabel;

    private Board board;

    private static int BOARD_SIZE = 8;
    private Player black;
    private Player white;
    private Player currentPlayer;

    private MainWindowController() {
        super();
    }
    public MainWindowController(Player black, Player white) {
        this();
        this.black = black;
        this.white = white;
        this.currentPlayer = black;
    }

    private void addCell(int rowId, int columnId) {
        int cell_id = rowId * BOARD_SIZE + columnId;
        if(board.isCorrectMove(cell_id))
            board = board.makeMove(cell_id);

        currentPlayer = nextPlayer();
        //whiteScore.setText(board.getWhiteScore());
        //blackScore.setText(board.getBlackScore());
        displayBoard();
    }

    private Player nextPlayer() {
        currentPlayerLabel.setText(currentPlayer.getName());
        return currentPlayer.equals(black) ? white : black;
    }

    private Site getCurrentSite() {
        return currentPlayer.equals(black) ? Site.PLAYER : Site.OPPONENT;
    }

    public void displayBoard() {
       // System.out.println("displaying board");
        tilePane.getChildren().clear();
        for(int i=0;i<BOARD_SIZE;i++)
            for(int j=0;j<BOARD_SIZE;j++) {
                Site site = board.getSite(i * BOARD_SIZE + j);
                Cell cell;
                if(site != null) {
                    String color = (site.equals(getCurrentSite())) ? "black" : "white";
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

        // todo: check if AI starts
    }
}
