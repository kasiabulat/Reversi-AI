package ui.windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import players.AI.FirstAvailableMovePlayer;
import players.HumanPlayer;
import ui.controllers.MainWindowController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/main_window.fxml"));
        MainWindowController mainWindowContronller = new MainWindowController(new FirstAvailableMovePlayer("AI1"), new FirstAvailableMovePlayer("AI2") );
        fxmlLoader.setController(mainWindowContronller);
        fxmlLoader.setRoot(mainWindowContronller);
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(new Scene(fxmlLoader.getRoot(), 500, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
