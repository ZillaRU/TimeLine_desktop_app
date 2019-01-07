import home.Main;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeUnit;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * @author: zilla0148
 * @date: 2019/1/7 1:41
 */
public class LogOutTest extends ApplicationTest {
    Stage stage;

    @Before
    public void setup() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication( Main.class );
        clickOn( "#nameField" ).write( "1w1w1w" );
        clickOn( "#passwordField" ).write( "1w1w1w" );
        clickOn( "#signInButton" );
        clickOn( "#closeBtn" );
        WaitForAsyncUtils.sleep( 2, TimeUnit.SECONDS );
    }

    @Test
    public void log_out() {
        clickOn( "#logOutBtn" );
        verifyThat( "#signInButton", isVisible() );
    }
}
