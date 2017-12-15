package ui.windows;

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
        MainWindowController mainWindowController =
                new MainWindowController(
                        new HumanUIPlayer("Kasia"),
                        new RandomizePlayPlayer("Kamil",new Random()));
                        //new FirstAvailableMovePlayer("ai"));

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


