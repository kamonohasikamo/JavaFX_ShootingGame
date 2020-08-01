package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet {
    double bullet_x;
    double bullet_y;
    double bullet_v;
    boolean isBulletExist;

    // constructor
    public Bullet() {
        bullet_v = 1.0;
        isBulletExist = false;
    }

    // shot Bullet
    public void enter(Player p) {
        isBulletExist = true;
        bullet_x = p.getPlayerX();
        bullet_y = p.getPlayerY();
    }

    public void move() {
        bullet_x += bullet_v;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillRect(bullet_x, bullet_y, Define.BULLET_WIDTH, Define.BULLET_HEIGHT);
    }
}
