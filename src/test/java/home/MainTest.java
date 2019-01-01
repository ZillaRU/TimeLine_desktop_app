package home;
import static java.util.function.Predicate.isEqual;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.testfx.assertions.api.Assertions.assertThat;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.StartUpController;

import home.ConstantSetting;
import home.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.junit.After;
import org.junit.Before;
//import org.junit.Test;
import org.testfx.robot.Motion;
import org.testfx.util.WaitForAsyncUtils;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class MainTest extends ApplicationTest {
    Stage stage;
    @Before public void setup() throws Exception {

        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);

        //   WaitForAsyncUtils.sleep(6, TimeUnit.SECONDS);
        //  FxToolkit.cleanupApplication(demoApplication);
        // Scene demoScene = FxToolkit.setupScene(() -> new Scene(new StackPane(),300, 100));
        //WaitForAsyncUtils.sleep(3, TimeUnit.SECONDS);
        // Setup and show Scene.

    }
    @After public void cleanup() {}

    @Test
    public void stage_should_be_visible() {
        // expect:
        //verifyThat(stage.isFullScreen(),equalTo(true));
        assertFalse(stage.isFullScreen());

    }
    @Test
    public void login_with_wrong_username() {

        String strname="cse";
        FxRobot robot=new FxRobot();
        robot.clickOn("#nameField").write("dd112211");
        robot.clickOn("#passwordField").write("dd112211");
        robot.clickOn("#signInButton");

    }


}