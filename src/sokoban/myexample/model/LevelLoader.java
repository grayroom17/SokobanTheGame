package sokoban.myexample.model;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class LevelLoader {
    ArrayList<String> buffer = new ArrayList<>();

    public LevelLoader(Path levels) {
        //StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            while (reader.ready()) {
                //builder.append(reader.readLine());
                //builder.append(System.lineSeparator());
                buffer.add(reader.readLine());
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        level = level > 60 ? level % 60 : level;
        level = level == 0 ? 1 : level;
        int indexOfLevelString = buffer.indexOf("Maze: " + level);
        int sizeX = Integer.parseInt(buffer.get(indexOfLevelString + 2).replace("Size X: ", ""));
        int sizeY = Integer.parseInt(buffer.get(indexOfLevelString + 3).replace("Size Y: ", ""));

        for (int i = 0; i < sizeY; i++) {
            String[] signs = buffer.get(indexOfLevelString + 7 + i).split("");
            if (signs.length != (sizeX))
                System.out.println("Не совпадает количество символов.");
            for (int j = 0; j < signs.length; j++) {

                int x = 10 + j * Model.FIELD_CELL_SIZE;
                int y = 10 + i * Model.FIELD_CELL_SIZE;

                switch (signs[j]) {
                    case " ":
                        continue;
                    case "X":
                        walls.add(new Wall(x, y));
                        break;
                    case "*":
                        boxes.add(new Box(x, y));
                        break;
                    case ".":
                        homes.add(new Home(x, y));
                        break;
                    case "&":
                        boxes.add(new Box(x, y));
                        homes.add(new Home(x, y));
                        break;
                    case "@":
                        player = new Player(x, y);
                        break;
                }
            }
        }

        return new GameObjects(walls,boxes,homes,player);
    }

    private boolean isAllObjectsMultiply(Set<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            if (!isMultiply(gameObject.x, gameObject.y)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMultiply(int x, int y) {
        return (x != 0 && y != 0 && x % (Model.FIELD_CELL_SIZE / 2) == 0 && y % (Model.FIELD_CELL_SIZE / 2) == 0);
    }

    private int getRandom() {
        return (10 + (new Random().nextInt(24) + 1) * 20);

    }

}
