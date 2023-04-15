package com.example.game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

import java.awt.*;

public class HelloController{
    static int rand = (int) (Math.random() * 100);
    @FXML
    private TextField Answer;

    @FXML
    private Label Hint;

    @FXML
    private AnchorPane Lefts;

    @FXML
    private ImageView Logo;

    @FXML
    private AnchorPane Rights;

    @FXML
    private Label Titlles;

    @FXML
    private AnchorPane Top;

    @FXML
    private Label help1;

    @FXML
    private Label help2;

    @FXML
    private Label help3;

    public void Gussed(ActionEvent e){
        int guess = Integer.parseInt(Answer.getText());
        if(guess == rand){
            Hint.setText("WIN!!");
        } else if (guess<rand) {
            Hint.setText("Guess Higher");
        } else if (guess>rand) {
            Hint.setText("Guess Lower");
        }
    }

}
