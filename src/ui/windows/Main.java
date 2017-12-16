package ui.windows;

import board.BoardFactory;
import game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import players.HumanUIPlayer;
import players.ai.RandomizePlayPlayer;
import ui.controllers.MainWindowController;

import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/main_window.fxml"));
        MainWindowController mainWindowController = new MainWindowController();

        HumanUIPlayer black = new HumanUIPlayer("Kasia",mainWindowController);
        RandomizePlayPlayer white = new RandomizePlayPlayer("Kamil", new Random());
        Game game = new Game(black,white,new BoardFactory(),mainWindowController);

        mainWindowController.setGame(game);
        mainWindowController.setPlayers(black,white);

        fxmlLoader.setController(mainWindowController);
        fxmlLoader.load();

        primaryStage.setTitle("Reversi");
        primaryStage.setScene(new Scene(fxmlLoader.getRoot(), 500, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


