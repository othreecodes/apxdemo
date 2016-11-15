package com.root.apxdemo.controllers;



import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.othree.apx.tester;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.root.apxdemo.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * genarated by APX file generation template
 * File name: LoginController.java
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */

    private Dao<User,Integer> UserDao;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void BackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/root/apxdemo/views/MainView.fxml"));

        stage.hide();
        stage.setScene(new Scene(root, 600, 400));
        stage.centerOnScreen();
        stage.show();

    }

    
    @FXML
    void LogmeIn(ActionEvent event) throws SQLException {

        String username = usernameField.getText();
        String password = passwordField.getText();
       ConnectionSource connectionSource = null;
        try {
            String databaseUrl = "jdbc:sqlite:db.sqlite";
            System.out.println(databaseUrl);
            connectionSource
                    = new JdbcConnectionSource(databaseUrl);
        } catch (SQLException ex) {
            Logger.getLogger(tester.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Using ORMlite's DAO
        UserDao = DaoManager.createDao(connectionSource,User.class);

        QueryBuilder<User,Integer> queryBuilder= UserDao.queryBuilder();
        User user =queryBuilder.where()
                .eq("username",username)
                .and()
                .eq("password",password)
                .queryForFirst();

        if(user==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No User Found with such credentials");
            alert.show();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Your Login was Successful\nThanks for using Demo app ");
            alert.show();
        }

        try {
            connectionSource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
