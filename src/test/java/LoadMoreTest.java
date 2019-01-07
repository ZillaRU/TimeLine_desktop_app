import home.Main;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

public class LoadMoreTest extends ApplicationTest {
    Stage stage;

    @Before
    public void setup() throws TimeoutException {
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication( Main.class );
        clickOn( "#nameField" ).write( "1w1w1w" );
        clickOn( "#passwordField" ).write( "1w1w1w" );
        clickOn( "#signInButton" );
        clickOn( "#closeBtn" );
        WaitForAsyncUtils.sleep( 2, TimeUnit.SECONDS );
    }

    @Test
    public void load_more_posts() {
        clickOn( "#loadMoreBtn" );
        verifyThat( lookup( "#postBox" ).nth( 3 ), isVisible() );
        WaitForAsyncUtils.sleep( 2, TimeUnit.SECONDS );
    }
}
