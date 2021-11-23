package sokoban.myexample.model;


import sokoban.myexample.controller.EventListener;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Set;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    GameObjects gameObjects;
    int currentLevel = 1;
    LevelLoader levelLoader;

    public Model(){
        try {
            levelLoader = new LevelLoader(Paths.get(getClass().getResource("../res/levels.txt").toURI()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void move(Direction direction) {
        if (checkWallCollision(gameObjects.player, direction))
            return;
        if (checkBoxCollisionAndMoveIfAvailable(direction))
            return;

        moveObject(gameObjects.player, direction);
        checkCompletion();

    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        Set<Wall> walls = gameObjects.getWalls();
        for (Wall wall : walls) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();
        Set<Box> boxes = gameObjects.getBoxes();
        Box currentBox = null;

        if (checkWallCollision(player, direction))
            return true;

        for (Box box : boxes) {
            if (player.isCollision(box, direction))
                currentBox = box;
        }

        if (currentBox == null)
            return false;

        if (checkWallCollision(currentBox, direction))
            return true;

        for (Box box : boxes) {
            if (currentBox == box)
                continue;
            if (currentBox.isCollision(box, direction))
                return true;
        }

           /* if (checkWallCollision(box, direction))
                return true;
            if (player.isCollision(box, direction)) {
                for (Box b : boxes) {
                    if (box == b)
                        continue;
                    if (box.isCollision(b, direction))
                        return true;

                }

                moveObject(box, direction);
                //moveObject(player, direction);
                return false;
            }
        }*/
        moveObject(currentBox, direction);

        return false;
    }

    public void checkCompletion() {
        Set<Home> homes = gameObjects.getHomes();
        Set<Box> boxes = gameObjects.getBoxes();
        int count = 0;
        for (Home home : homes) {
            for (Box box : boxes) {
                if (home.x == box.x && home.y == box.y)
                    count++;
            }
        }
        if (count == boxes.size())
            eventListener.levelCompleted(currentLevel);
    }

    public void moveObject(Movable movableObject, Direction direction) {
        switch (direction) {
            case LEFT:
                movableObject.move(-Model.FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                movableObject.move(Model.FIELD_CELL_SIZE, 0);
                break;
            case UP:
                movableObject.move(0, -Model.FIELD_CELL_SIZE);
                break;
            case DOWN:
                movableObject.move(0, Model.FIELD_CELL_SIZE);
                break;
        }
    }

}
