package functionTest;

import home.Main;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


public class LogOutTest extends ApplicationTest {
    Stage stage;

    @Before
    public void setup() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication( Main.class );
        clickOn( "#nameField" ).write( TestConstants.USERNAME);
        clickOn( "#passwordField" ).write( TestConstants.PASSWORD);
        clickOn( "#signInButton" );
        clickOn( "#closeBtn" );
       // WaitForAsyncUtils.sleep( 2, TimeUnit.SECONDS );
    }

    @Test
    public void log_out() {
        clickOn( "#logOutBtn" );
        verifyThat( "#signInButton", isVisible() );
    }
}
