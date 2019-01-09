import home.Main;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeUnit;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class PostTest extends ApplicationTest {
    private static final Logger LOG = LoggerFactory.getLogger( PostTest.class );
    Stage stage;
    private int stepNo;

    @Before
    public void setup() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication( Main.class );
        clickOn("#nameField").write("qqq123");
        clickOn("#passwordField").write("qqq123");
        clickOn( "#signInButton" );
        clickOn( "#closeBtn" );
    }

    @After
    public void cleanup() {
    }

    @Test
    public void post_with_no_images_and_comments_and_show_just_now() {
        //todo:post->submit->close->verify
        step( "Open application", () -> {
            verifyThat( "#newPostBtn", isVisible() );
        } );

        step( "Start new post", () -> {
            clickOn( "#newPostBtn" );
            clickOn( "#contentText" ).write( "adksfnckadsbvfkdnv!234341@#$测试数据" );
        } );

        step( "Complete post", () -> {
            clickOn( "#submitBtn" );
            WaitForAsyncUtils.sleep( 2, TimeUnit.SECONDS );
            verifyThat(lookup("#username").nth(0), hasText("qqq123"));
            verifyThat( lookup( "#content" ).nth( 0 ), hasText( "adksfnckadsbvfkdnv!234341@#$测试数据" ) );
            verifyThat( lookup( "#updateTime" ).nth( 0 ), hasText( "Just now" ) );
        } );
    }

    @Test
    public void post_with_images_and_comments_and_show_just_now() {
        //todo:post->submit->close->verify
        step( "Open application", () -> {
            verifyThat( "#newPostBtn", isVisible() );
        } );

        step( "Start new post", () -> {
            clickOn( "#newPostBtn" );
            clickOn( "#contentText" ).write( "adksfnckadsbvfkdnv!234341@#$测试数据" );
            clickOn( "#uploadImageBtn" ).press( KeyCode.RIGHT ).press( KeyCode.ENTER );

        } );

        step( "Complete post", () -> {
            clickOn( "#submitBtn" );
            WaitForAsyncUtils.sleep( 2, TimeUnit.SECONDS );
            verifyThat(lookup("#username").nth(0), hasText("qqq123"));
            verifyThat( lookup( "#content" ).nth( 0 ), hasText( "adksfnckadsbvfkdnv!234341@#$测试数据" ) );
            verifyThat( lookup( "#updateTime" ).nth( 0 ), hasText( "Just now" ) );
        } );
    }

    @Test
    public void show_main_page() {
        if (lookup("#postBox").query().isVisible()) {
            verifyThat(lookup("#postBox").nth(1), isVisible());
            verifyThat(lookup("#postBox").nth(2), isVisible());
            verifyThat(lookup("#postBox").nth(3), isVisible());
            System.out.println(lookup("#username").nth(1).toString());
        } else {
            verifyThat("#hintLabel", isVisible());

        }

    }


//            clickOn("#uploadImageBtn").press(KeyCode.RIGHT).press(KeyCode.ENTER);

    private void step(final String step, final Runnable runnable) {
        ++stepNo;
        LOG.info( "STEP {}: Begin - {}", stepNo, step );
        runnable.run();
        LOG.info( "STEP {}:   End - {}", stepNo, step );
    }
}
