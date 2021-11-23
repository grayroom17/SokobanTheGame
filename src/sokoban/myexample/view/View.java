package sokoban.myexample.view;


import sokoban.myexample.controller.Controller;
import sokoban.myexample.controller.EventListener;
import sokoban.myexample.model.GameObjects;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,500);
        getContentPane().add(BorderLayout.CENTER,field);


        //setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);

    }

    public GameObjects getGameObjects(){
        return controller.getGameObjects();
    }

    public void setEventListener(EventListener eventListener){
        field.setEventListener(eventListener);
    }

    public void update(){
        field.repaint();
    }

    public void completed(int level){
        this.update();
        JOptionPane.showMessageDialog(this,"Level " + level + " have been completed");
        controller.startNextLevel();
    }
}
