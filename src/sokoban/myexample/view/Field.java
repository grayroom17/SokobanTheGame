package sokoban.myexample.view;



import sokoban.myexample.controller.EventListener;
import sokoban.myexample.model.Direction;
import sokoban.myexample.model.GameObject;
import sokoban.myexample.model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {
    private View view;
    EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
        setLayout(new BorderLayout());
        setSize(500,500);
    }

    public void paint(Graphics g) {
//        Player player = new Player(50,50);
//        player.draw(g);
//        Box box = new Box(85,60);
//        box.draw(g);
//        Home home = new Home(100,100);
//        home.draw(g);

        g.setColor(Color.black);
        g.fillRect(0,0,500,500);
        GameObjects gameObjects = view.getGameObjects();
        for (GameObject gameObject : gameObjects.getAll()) {
            gameObject.draw(g);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R:
                    eventListener.restart();
                    break;
            }
        }
    }
}
