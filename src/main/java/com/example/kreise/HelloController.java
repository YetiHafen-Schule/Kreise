package com.example.kreise;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HelloController {

    @FXML
    private Pane canvas;

    @FXML
    private CheckBox checkBoxFilled;

    @FXML
    private Button btnStartStop;

    private boolean running = false;

    @FXML
    protected void onBtnCreateClick() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);

        int posY = (int) (Math.random() * canvas.getHeight());
        int posX = (int) (Math.random() * canvas.getWidth());

        Color color = Color.rgb(red, green, blue);

        Circle c = new Circle(posX, posY, 40, color);

        if(!checkBoxFilled.isSelected()) {
            c.setFill(new Color(0,0,0,0));
        }

        c.setStroke(color);

        canvas.getChildren().add(c);
    }

    @FXML
    protected void onBtnClearClick() {
        canvas.getChildren().clear();
    }

    @FXML
    protected void onBtnStartStopClick() {
        if(!running) {
            btnStartStop.setText("Stop");
            new Thread(this::addCircleLoop).start();
        } else {
            btnStartStop.setText("Start");
        }
        running = !running;
    }

    private void addCircleLoop() {
        while(running) {
            Platform.runLater(this::onBtnCreateClick);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread exit");
    }

}