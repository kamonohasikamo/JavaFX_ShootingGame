package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
    double player_x;
    double player_y;

    // PlayerImage
    Image player = new Image(this.getClass().getResource("img/player.png").toExternalForm());

    public void move(byte[] key) {
        player_x += (key[Define.KEY_RIGHT] - key[Define.KEY_LEFT]);
        player_y += (key[Define.KEY_DOWN] - key[Define.KEY_UP]);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(player, player_x, player_y, Define.PLAYER_SIZE, Define.PLAYER_SIZE);
    }
}
