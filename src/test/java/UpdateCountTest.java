import home.Main;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

/**
 * @author: zilla0148
 * @date: 2019/1/7 1:58
 */
public class UpdateCountTest extends ApplicationTest {
    Stage stage1;
    Stage stage2;

    @Test
    public void setup() throws Exception {
        stage1 = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication( Main.class );
        clickOn( "#nameField" ).write( "1w1w1w" );
        clickOn( "#passwordField" ).write( "1w1w1w" );
        clickOn( "#signInButton" );
        clickOn( "#closeBtn" );
//        WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
//
//        stage2 = FxToolkit.registerStage();
//        FxToolkit.setupApplication( Main.class );
//        clickOn( "#nameField" ).write( "qqq123" );
//        clickOn( "#passwordField" ).write( "qqq123" );
//        clickOn( "#signInButton" );
//        clickOn( "#closeBtn" );
//        WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
    }


}
