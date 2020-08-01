package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
    private double player_x;
    private double player_y;
    Image player;
    Bullet[] bullet = new Bullet[Define.BULLET_MAX];

    // constructor
    public Player () {
        // PlayerImage
        player = new Image(this.getClass().getResource("img/player.png").toExternalForm());
        player_x = 0;
        player_y = 0;
        for (int i = 0; i < Define.BULLET_MAX; i++) {
            bullet[i] = new Bullet();
        }
    }

    public double getPlayerX() {
        return player_x;
    }
    public void setPlayerX(double x) {
        this.player_x = x;
    }

    public double getPlayerY() {
        return player_y;
    }

    public void setPlayerY(double y) {
        this.player_y = y;
    }

    public void move(byte[] key) {
        player_x += (key[Define.KEY_RIGHT] - key[Define.KEY_LEFT]);
        player_y += (key[Define.KEY_DOWN] - key[Define.KEY_UP]);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(player, player_x, player_y, Define.PLAYER_SIZE, Define.PLAYER_SIZE);
    }
}
