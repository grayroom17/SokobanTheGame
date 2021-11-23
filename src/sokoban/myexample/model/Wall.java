package sokoban.myexample.model;

import java.awt.*;

public class Wall extends CollisionObject{
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.orange);
        graphics.drawRect(x - width / 2, y - height / 2, width, height);
    }

    public Wall(int x, int y) {
        super(x, y);
    }
}
