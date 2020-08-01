package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    //========================================
    // Constant definition
    //========================================
    static final int WINDOW_WIDTH = 1000;
    static final int WINDOW_HEIGHT = 600;
    static final Color BACKGROUNDCOLOR = Color.BLACK;
    static final int KEY_RIGHT = 0;
    static final int KEY_LEFT  = 1;
    static final int KEY_UP    = 2;
    static final int KEY_DOWN  = 3;
    byte[] key = new byte[4];
    double player_x = 100;
    double player_y = 100;

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
                // 全画面の消去処理で再描画
                gc.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

                // Pleyer の座標更新処理
                player_x += (key[KEY_RIGHT] - key[KEY_LEFT]);
                player_y += (key[KEY_DOWN] - key[KEY_UP]);

                // その位置に描画
                gc.drawImage(player, player_x, player_y, 30, 30);
                try {
                    Thread.sleep(3);
                } catch(Exception e) {

                }
            }
        });
        gameThread.setDaemon(true);
        gameThread.start();

        // On press key
        scene.setOnKeyPressed(e -> {
            System.out.println("KeyCode = " + e.getCode());
            switch (e.getCode()) {
                case RIGHT: key[KEY_RIGHT] = 1; break;
                case LEFT:  key[KEY_LEFT]  = 1; break;
                case UP:    key[KEY_UP]    = 1; break;
                case DOWN:  key[KEY_DOWN]  = 1; break;
            }
        });

        // Release key
        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case RIGHT: key[KEY_RIGHT] = 0; break;
                case LEFT:  key[KEY_LEFT]  = 0; break;
                case UP:    key[KEY_UP]    = 0; break;
                case DOWN:  key[KEY_DOWN]  = 0; break;
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
