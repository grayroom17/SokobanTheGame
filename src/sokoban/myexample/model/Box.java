package sokoban.myexample.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.drawRect(x - width / 2, y - height / 2, width, height);
//        graphics.setColor(Color.orange);
//        graphics.fillRect(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
