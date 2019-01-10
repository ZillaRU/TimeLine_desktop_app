package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import home.Main;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;

import java.util.Random;

import static org.junit.Assert.*;

public class RegisterControllerTest {
    Stage stage;
    private RegisterController registerController=new RegisterController();

    @Before
    public void setup() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication( Main.class );
        registerController.nameField = new JFXTextField();
        registerController.passwordField = new JFXPasswordField();
        registerController.rePasswordField = new JFXPasswordField();
    }

    @Test
    public void handle_SignUp_JFXButton_with_empty_username() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                registerController.nameField.setText("");
                registerController.passwordField.setText("dd112211");
                registerController.rePasswordField.setText("dd112211");
                String alert = registerController.handleSignUpJFXButtonAction();
                assertEquals("The required information is incomplete.", alert);
            }});
    }

    @Test
    public void handle_SignUp_JFXButton_with_illegal_username() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                registerController.nameField.setText("qqqqqqqq");
                registerController.passwordField.setText("dd112211");
                registerController.rePasswordField.setText("dd112211");
                String alert=registerController.handleSignUpJFXButtonAction();
                assertEquals("Username format error.Only contain 0-9,a-z,A-Z length: 6-18",alert);
            }});

    }

    @Test
    public void handle_SignUp_JFXButton_with_illegal_password(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                registerController.nameField.setText("qqqq123");
                registerController.passwordField.setText("112211");
                registerController.rePasswordField.setText("112211");
                String alert=registerController.handleSignUpJFXButtonAction();
                assertEquals("Password format error.Only contain 0-9,a-z,A-Z length: 6-18",alert);
            }});
    }

    @Test
    public void handle_SignUp_JFXButton_with_inconsistent_password(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                registerController.nameField.setText("qqqq123");
                registerController.passwordField.setText("dd1112211");
                registerController.rePasswordField.setText("dd112211");
                String alert=registerController.handleSignUpJFXButtonAction();
                assertEquals("Inconsistent passwords!",alert);
            }});
    }

    @Test
    public void handle_SignUp_JFXButton_with_existed_username(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                registerController.nameField.setText("qqq123");
                registerController.passwordField.setText("dd112211");
                registerController.rePasswordField.setText("dd112211");
                String alert=registerController.handleSignUpJFXButtonAction();
                assertEquals("The username already exists",alert);
            }});
    }

    @Test
    public void handle_SignUp_JFXButton_with_right_username_password(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                int random = new Random().nextInt( 900 ) + 100;
                registerController.nameField.setText( "www" + random );
                registerController.passwordField.setText("dd112211");
                registerController.rePasswordField.setText("dd112211");
                String alert=registerController.handleSignUpJFXButtonAction();
                assertEquals("Please log in and enjoy your time.",alert);
            }});
    }
}