package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    //========================================
    // Constant definition
    //========================================
    static final int WINDOW_WIDTH = 1000;
    static final int WINDOW_HEIGHT = 600;
    static final Color BACKGROUNDCOLOR = Color.BLACK;

    @Override
    public void start(Stage theStage) {
        // create Group
        Group rootGroup = new Group();
        // create Scene
        Scene scene = new Scene(rootGroup, WINDOW_WIDTH, WINDOW_HEIGHT, BACKGROUNDCOLOR);

        // create Canvas
        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        rootGroup.getChildren().add(canvas);

        // PlayerImage
        Image player = new Image(this.getClass().getResource("img/player.png").toExternalForm());

        theStage.setScene(scene);
        theStage.setTitle("ShootingGame");
        theStage.show();
        Thread gameThread = new Thread(() -> {
            while(true) {
                gc.drawImage(player, 50, 50);
                try {
                    Thread.sleep(2);
                } catch(Exception e) {

                }
            }
        });
        gameThread.setDaemon(true);
        gameThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
