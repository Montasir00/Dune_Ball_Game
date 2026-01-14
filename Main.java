import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameController controller = new GameController();
        Scene scene = new Scene(controller.getRoot(), 800, 600);
        controller.initKeyListeners(scene);

        primaryStage.setTitle("Dune Ball Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        controller.startGameLoop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
