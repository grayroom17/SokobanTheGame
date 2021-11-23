package sokoban.myexample.model;

import java.awt.*;

public class Home extends GameObject {
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.drawRect(x - width / 2, y - height / 2, width, height);
    }

    public Home(int x, int y) {
        super(x, y, 2, 2);
    }

}
