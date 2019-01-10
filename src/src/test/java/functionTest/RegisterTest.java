package functionTest;

import home.Main;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class RegisterTest extends ApplicationTest {
    Stage stage;
    Application main;

    @Before
    public void setup() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        main = FxToolkit.setupApplication( Main.class );
        clickOn( "#signUpTab" );
    }

    @After
    public void cleanup() throws TimeoutException {
        FxToolkit.cleanupStages();
        FxToolkit.cleanupApplication(main);
    }


    @Test
    public void register_with_empty_username_or_informal_username() {
        clickOn( "#passwordField" ).write( "dd112211" );
        clickOn( "#rePasswordField" ).write( "dd112211" );
        clickOn( "#signUpJFXButton" );
        verifyThat( "#closeBtn", isVisible() );
        verifyThat( "#contentLabel", hasText( "The required information is incomplete." ) );
        clickOn( "#closeBtn" );

        WaitForAsyncUtils.sleep( 3, TimeUnit.SECONDS );
        clickOn( "#nameField" ).write( "qq" );//过短
        clickOn( "#signUpJFXButton" );
        WaitForAsyncUtils.sleep( 2, TimeUnit.SECONDS );
        verifyThat( "#contentLabel", hasText( "Username format error.\nOnly contain 0-9,a-z,A-Z length: 6-18" ) );
        clickOn( "#closeBtn" );
        WaitForAsyncUtils.sleep( 2, TimeUnit.SECONDS );
        clickOn( "#nameField" ).write( "qqqqq" );//只有字母
        clickOn( "#signUpJFXButton" );
        verifyThat( "#contentLabel", hasText( "Username format error.\nOnly contain 0-9,a-z,A-Z length: 6-18" ) );
        clickOn( "#closeBtn" );
        WaitForAsyncUtils.sleep( 2, TimeUnit.SECONDS );
        clickOn( "#nameField" ).write( "123!" );//非法字符
        clickOn( "#signUpJFXButton" );
        verifyThat( "#contentLabel", hasText( "Username format error.\nOnly contain 0-9,a-z,A-Z length: 6-18" ) );

    }

    @Test
    public void register_with_empty_password_or_informal_password() {
        clickOn( "#nameField" ).write( "qqqq123" );
        clickOn( "#signUpJFXButton" );
        verifyThat( "#contentLabel", hasText( "The required information is incomplete." ) );
        clickOn( "#closeBtn" );
        WaitForAsyncUtils.sleep( 3, TimeUnit.SECONDS );
        clickOn( "#passwordField" ).write( "112211" );
        clickOn( "#rePasswordField" ).write( "112211" );
        clickOn( "#signUpJFXButton" );
        verifyThat( "#contentLabel", hasText( "Password format error.\nOnly contain 0-9,a-z,A-Z length: 6-18" ) );
    }

    @Test
    public void register_with_wrong_repassword() {
        clickOn( "#nameField" ).write( "qqqq123" );
        clickOn( "#passwordField" ).write( "dd112211" );
        clickOn( "#rePasswordField" ).write( "ddd112211" );
        clickOn( "#signUpJFXButton" );
        verifyThat( "#contentLabel", hasText( "Inconsistent passwords!" ) );
    }

    @Test
    public void register_with_exist_username() {
        clickOn( "#nameField" ).write( "qqq123" );
        clickOn( "#passwordField" ).write( "dd112211" );
        clickOn( "#rePasswordField" ).write( "dd112211" );
        clickOn( "#signUpJFXButton" );
        verifyThat( "#headLabel", hasText( "Registration failed" ) );
        verifyThat( "#contentLabel", hasText( "The username already exists" ) );
    }

    @Test
    public void register_with_right_username_and_password() {
        int random = new Random().nextInt( 900 ) + 100;
        clickOn( "#nameField" ).write( "qqq" + random );
        clickOn( "#passwordField" ).write( random + "qqq" );
        clickOn( "#rePasswordField" ).write( random + "qqq" );
        clickOn( "#signUpJFXButton" );
        WaitForAsyncUtils.sleep( 1, TimeUnit.SECONDS );
        verifyThat( "#headLabel", hasText( "Registration succeeded" ) );
        verifyThat( "#contentLabel", hasText( "Welcome qqq" + random + " !\nPlease log in and enjoy your time." ) );
    }
}

