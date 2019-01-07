import home.Main;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

//import org.junit.Test;

public class LoginTest extends ApplicationTest {
    Stage stage;

    @Before
    public void setup() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication( Main.class );
    }

    @After
    public void cleanup() {

    }

    @Test
    public void stage_should_be_visible() {
        assertFalse( stage.isFullScreen() );

    }

    @Test
    public void login_with_empty_username() {
        FxRobot robot = new FxRobot();
        robot.clickOn( "#passwordField" ).write( "dd112211" );
        robot.clickOn( "#signInButton" );
        verifyThat( "#closeBtn", isVisible() );
        verifyThat( "#headLabel", hasText( "Prompt" ) );
        verifyThat( "#contentLabel", hasText( "Username cannot be empty." ) );
    }

    @Test
    public void login_with_wrong_username() {
        FxRobot robot = new FxRobot();
        robot.clickOn( "#nameField" ).write( "wrongusr" );
        robot.clickOn( "#passwordField" ).write( "dd112211" );
        robot.clickOn( "#signInButton" );
        WaitForAsyncUtils.sleep( 1, TimeUnit.SECONDS );
        verifyThat( "#closeBtn", isVisible() );
        verifyThat( "#headLabel", hasText( "Prompt" ) );
        verifyThat( "#contentLabel", hasText( "Wrong username and/or password" ) );
    }

    @Test
    public void login_with_empty_password() {
        FxRobot robot = new FxRobot();
        robot.clickOn( "#nameField" ).write( "qqq123" );
        robot.clickOn( "#signInButton" );
        verifyThat( "#closeBtn", isVisible() );
        verifyThat( "#headLabel", hasText( "Prompt" ) );
        verifyThat( "#contentLabel", hasText( "Password cannot be empty." ) );
    }

    @Test
    public void login_with_wrong_password() {
        FxRobot robot = new FxRobot();
        robot.clickOn( "#nameField" ).write( "qqq123" );
        robot.clickOn( "#passwordField" ).write( "dd" );
        robot.clickOn( "#signInButton" );
        verifyThat( "#closeBtn", isVisible() );
        verifyThat( "#headLabel", hasText( "Prompt" ) );
        verifyThat( "#contentLabel", hasText( "Wrong username and/or password" ) );
    }

    @Test
    public void login_with_right_password() {
        FxRobot robot = new FxRobot();
        robot.clickOn( "#nameField" ).write( "qqq123" );
        robot.clickOn( "#passwordField" ).write( "dd112211" );
        robot.clickOn( "#signInButton" );
        verifyThat( "#closeBtn", isVisible() );
        verifyThat( "#headLabel", hasText( "Prompt" ) );
        verifyThat( "#contentLabel", hasText( "Welcome qqq123" ) );
    }
}