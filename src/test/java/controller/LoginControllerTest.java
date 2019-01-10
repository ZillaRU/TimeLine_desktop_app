package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import home.Main;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest extends ApplicationTest {
    Stage stage;

    private LoginController loginController = new LoginController();

    @Before
    public void setup() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
        loginController.app = Main.getApp();
        loginController.nameField = new JFXTextField();
        loginController.passwordField = new JFXPasswordField();
    }

    @Test
    public void handle_LogIn_JFXButtonAction_with_username_empty() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginController.nameField.setText("");
                loginController.passwordField.setText("dd112211");
                String alert = loginController.handleLogInJFXButtonAction();
                assertEquals("Username cannot be empty.", alert);
//                Mockito.verify(alertHelper).showJDialog(argThat(new IStage()),
//                        eq(ConstantSetting.ALERT_TITLE),
//                        anyString());
            }
        });
    }

    @Test
    public void handle_LogIn_JFXButtonAction_with_password_empty() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginController.nameField.setText("qqq123");
                loginController.passwordField.setText("");
                String alert = loginController.handleLogInJFXButtonAction();
                assertEquals("Password cannot be empty.", alert);
            }
        });
    }

    @Test
    public void handle_LogIn_JFXButtonAction_with_password_or_username_wrong() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginController.nameField.setText("qqq123");
                loginController.passwordField.setText("dd123");
                String alert = loginController.handleLogInJFXButtonAction();
                assertEquals("Wrong username and/or password", alert);
            }
        });
    }

    @Test
    public void handle_LogIn_JFXButtonAction_with_correct_username_password() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginController.nameField.setText("qqq123");
                loginController.passwordField.setText("dd112211");
                String alert = loginController.handleLogInJFXButtonAction();
                assertEquals("Welcome qqq123", alert);
            }
        });
    }
}
//class IStage extends ArgumentMatcher<Stage> {
//    public boolean matches(Object stage) {
//        return (stage instanceof Stage)|| stage==null ;
//    }
//}