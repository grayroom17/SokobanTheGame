package sokoban.myexample.controller;


import sokoban.myexample.model.Direction;
import sokoban.myexample.model.GameObjects;
import sokoban.myexample.model.Model;
import sokoban.myexample.view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    public Controller() {
        view = new View(this);
        model = new Model();
        view.init();
        model.restart();
        view.setEventListener(this);
        model.setEventListener(this);
    }


    public static void main(String[] args) {

        Controller controller = new Controller();
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }
}

