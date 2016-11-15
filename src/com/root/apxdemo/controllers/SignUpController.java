package com.root.apxdemo.controllers;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.othree.apx.tester;
import com.root.apxdemo.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * genarated by APX file generation template
 * File name: SignUpController.java
 */
public class SignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Dao<User,Integer> UserDao;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button loginButton1;

    @FXML
    private PasswordField passwordField1;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void BackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/root/apxdemo/views/MainView.fxml"));

        stage.hide();
        stage.setScene(new Scene(root, 600, 400));
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void SignUp(ActionEvent event) throws SQLException {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = passwordField1.getText();

        if(username.trim().isEmpty() || password.trim().isEmpty() ||confirmPassword.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incomplete form");
            alert.setContentText("Please complete the form");
            alert.show();
            return;
        }


        ConnectionSource connectionSource = null;
        try {
            String databaseUrl = "jdbc:sqlite:db.sqlite";
            System.out.println(databaseUrl);
            connectionSource
                    = new JdbcConnectionSource(databaseUrl);
        } catch (SQLException ex) {
            Logger.getLogger(tester.class.getName()).log(Level.SEVERE, null, ex);
        }

        UserDao = DaoManager.createDao(connectionSource,User.class);
        QueryBuilder<User,Integer> queryBuilder = UserDao.queryBuilder();
        List<User> users = queryBuilder.where()
                .eq("username",username)
                .query();
        if(!users.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("User with username "+username+" Already exists.\n Please try another");
            alert.show();

        }
        else{
            if(password.equalsIgnoreCase(confirmPassword)){
                User user = new User(username,password);

                UserDao.create(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("User created Sucessfully. You can login now");
                alert.show();
                alert.setResultConverter(new Callback<ButtonType, ButtonType>() {
                    @Override
                    public ButtonType call(ButtonType buttonType) {
                        try {
                            ToLogin();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });

            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Passwords do not match");
                alert.show();
            }

        }
        try {
            connectionSource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void ToLogin() throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/root/apxdemo/views/LoginView.fxml"));

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
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        initX = me.getScreenX() - stage.getX();
        initY = me.getScreenY() - stage.getY();

    }

    @FXML
    void dragged(MouseEvent me) {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        stage.setX(me.getScreenX() - initX);
        stage.setY(me.getScreenY() - initY);
    }
    
}
