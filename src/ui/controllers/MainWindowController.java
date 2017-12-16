package ui.controllers;

import board.Board;
import board.Board.Site;
import board.BoardFactory;
import game.Game;
import game.ui.GameUI;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import players.Player;
import players.PlayerUI;
import ui.components.Cell;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

public class MainWindowController implements GameUI, PlayerUI {
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

    private static final int BOARD_SIZE = 8;
    private Player black;
    private Player white;
    private Game game;
    private ArrayList<Cell> cells;

    public void setGame(Game game) {
        this.game = game;
    }
    public void setPlayers(final Player black, final Player white) {
        this.black = black;
        this.white = white;
    }

    private Site getCurrentSite(Player currentPlayer) {
        return currentPlayer.equals(black) ? Site.PLAYER : Site.OPPONENT;
    }

    private void addCells() {
        cells = new ArrayList<Cell>();
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) {
                final Cell cell;
                cell = new Cell(i, j);
                cell.showDisk();
                cells.add(cell);
                tilePane.getChildren().addAll(cell);
            }
    }

    @FXML
    public void initialize() {
        tilePane.getChildren().clear();
        tilePane.setPadding(new Insets(20, 20, 20, 20));
        tilePane.setPrefColumns(8);
        tilePane.setPrefRows(8);
        tilePane.setMaxSize(380, 380);
        tilePane.setStyle("-fx-background-color: forestgreen;");

        final BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.getStartingBoard();

        addCells();
        game.evaluate(board,black);
    }

    @Override
    public void endGame(@Nullable Player winner) {
        Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setResizable(false);
        VBox dialogVbox = new VBox(20);

        String gameEndMessage;
        if (winner == null) gameEndMessage = "Draw!";
        else gameEndMessage = "Player " + winner.getName() + " wins!";

        Label message = new Label(gameEndMessage);
        message.setAlignment(Pos.CENTER);
        dialogVbox.getChildren().add(message);
        dialog.setScene(new Scene(dialogVbox, 260, 40));
        dialog.show();
    }

    @Override
    public void printCurrentPlayer(@NotNull Player player) {
        currentPlayerLabel.setText(player.getName());
    }

    @Override
    public void displayBoard(@NotNull Board board, @NotNull Player currentPlayer) {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) {
                final Site site = board.getSite(i * BOARD_SIZE + j);
                final Cell cell = cells.get(i*BOARD_SIZE + j);
                if (site != null) {
                    final String color = site.equals(getCurrentSite(currentPlayer)) ? "black" : "white";
                    cell.setColor(color);
                    cell.showDisk();
                } else {
                    cell.hideDisk();
                }
            }

        blackScore.setText(String.valueOf(board.getScore(Site.PLAYER)));
        whiteScore.setText(String.valueOf(board.getScore(Site.OPPONENT)));
    }

    @NotNull
    @Override
    public Player nextPlayer(@NotNull Player currentPlayer) {
        return currentPlayer.equals(black) ? white : black;
    }

    @Override
    public void beginGame(@NotNull Player black, @NotNull Player white) {}

    @Override
    public void representMove(int move, @NotNull Function0<Unit> onDecided) {
        Cell cell = cells.get(move);
        cell.setColor("grey");
        cell.setOnClick(onDecided);
        cell.showDisk();
    }
}
