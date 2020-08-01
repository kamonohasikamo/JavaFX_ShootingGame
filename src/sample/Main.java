package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
    byte[] key = new byte[5];
    Player player;

    @Override
    public void start(Stage theStage) {
        // create Player
        player = new Player();
        // create Group
        Group rootGroup = new Group();
        // create Scene
        Scene scene = new Scene(rootGroup, Define.WINDOW_WIDTH, Define.WINDOW_HEIGHT, Define.BACKGROUNDCOLOR);

        // create Canvas
        Canvas canvas = new Canvas(Define.WINDOW_WIDTH, Define.WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        rootGroup.getChildren().add(canvas);

        theStage.setScene(scene);
        theStage.setTitle("ShootingGame");
        theStage.show();
        Thread gameThread = new Thread(() -> {
            while(true) {
                // 全画面の消去処理で再描画
                gc.clearRect(0, 0, Define.WINDOW_WIDTH, Define.WINDOW_HEIGHT);

                // Pleyer の座標更新処理
                player.move(key);

                // その位置に描画
                player.draw(gc);

                // Bullet クラスの呼び出し
                for (int i = 0; i < Define.BULLET_MAX; i++) {
                    if (player.bullet[i].isBulletExist) {
                        player.bullet[i].move();
                        player.bullet[i].draw(gc);
                    }
                }

                // Spaceキーを押されたら生成
                if (key[Define.KEY_SPACE] == 1) {
                    for (int i = 0; i < Define.BULLET_MAX; i++) {
                        if (!player.bullet[i].isBulletExist) {
                            player.bullet[i].enter(player);
                            break;
                        }
                    }
                }

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
            setKeyCodeType(true, e);
        });

        // Release key
        scene.setOnKeyReleased(e -> {
            setKeyCodeType(false, e);
        });

    }

    public void setKeyCodeType(boolean isOnPress, KeyEvent e) {
        byte setType = (byte) ((isOnPress) ? 1 : 0);
        switch (e.getCode()) {
            case RIGHT: key[Define.KEY_RIGHT] = setType; break;
            case LEFT:  key[Define.KEY_LEFT]  = setType; break;
            case UP:    key[Define.KEY_UP]    = setType; break;
            case DOWN:  key[Define.KEY_DOWN]  = setType; break;
            case SPACE: key[Define.KEY_SPACE] = setType; break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
