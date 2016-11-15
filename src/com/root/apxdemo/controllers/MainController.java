package com.root.apxdemo.controllers;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * genarated by APX file generation template
 * File name: MainController.java
 */
public class MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button quitButton;
     @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    public void quit(ActionEvent ev){
        Platform.exit();
    }
    
    
    @FXML
    void login(ActionEvent event) throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/root/apxdemo/views/LoginView.fxml"));
        
        stage.hide();
        stage.setScene(new Scene(root, 400, 600));
        stage.centerOnScreen();
        stage.show();
        
    }

    @FXML
    void signUpAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/root/apxdemo/views/SignUpView.fxml"));

        stage.hide();
        stage.setScene(new Scene(root, 400, 600));
        stage.centerOnScreen();
        stage.show();
    }


    //Enable Dragging of the stage since it has no window
    private double initX;
    private double initY;

    @FXML
    void pressed(MouseEvent me) {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        initX = me.getScreenX() - stage.getX();
        initY = me.getScreenY() - stage.getY();

    }

    @FXML
    void dragged(MouseEvent me) {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setX(me.getScreenX() - initX);
        stage.setY(me.getScreenY() - initY);
    }
    
}
