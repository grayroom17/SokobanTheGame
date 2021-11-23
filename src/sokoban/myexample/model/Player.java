package sokoban.myexample.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.drawOval(x - width / 2, y - height / 2, width, height);
//        graphics.setColor(Color.BLUE);
//        graphics.fillOval(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;

    }
}
