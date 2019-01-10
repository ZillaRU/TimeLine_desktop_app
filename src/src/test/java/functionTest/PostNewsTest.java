package functionTest;

import home.Main;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
public class PostNewsTest extends ApplicationTest {
    Stage stage1;
    private static final Logger LOG = LoggerFactory.getLogger( PostNewsTest.class );
    private int stepNo;
    Application main1;
    @Before
    public void setup() throws Exception {
        stage1 = FxToolkit.registerPrimaryStage();
        main1=FxToolkit.setupApplication( Main.class );
        clickOn( "#nameField" ).write(TestConstants.USERNAME);
        clickOn( "#passwordField" ).write(TestConstants.PASSWORD);
        clickOn( "#signInButton" );
        clickOn( "#closeBtn" );
    }

    @After
    public void cleanup() throws TimeoutException {
        FxToolkit.cleanupStages();
        FxToolkit.cleanupApplication(main1);
    }

    @Test
    public void post_with_no_image_and_comments_and_show_just_now() {
        step( "Open application", () -> {
            verifyThat( "#newPostBtn", isVisible() );
        } );

        step( "Start new post", () -> {
            clickOn( "#newPostBtn" );
            clickOn( "#contentText" ).write( TestConstants.TESTTEXT );
        } );

        step( "Complete post", () -> {
            clickOn( "#submitBtn" );
           // WaitForAsyncUtils.sleep( 2, TimeUnit.SECONDS );
            verifyThat( lookup( "#username" ).nth( 0 ), hasText( TestConstants.USERNAME) );
            verifyThat( lookup( "#content" ).nth( 0 ), hasText( TestConstants.TESTTEXT ) );
            verifyThat( lookup( "#updateTime" ).nth( 0 ), hasText( "Just now" ) );
            clickOn( "#closeBtn" );
        } );

    }

    @Test
    public void post_with_images_and_comments_and_show_just_now() {
        step( "Open application", () -> {
            verifyThat( "#newPostBtn", isVisible() );
        } );

        step( "Start new post", () -> {
            clickOn( "#newPostBtn" );
            clickOn( "#contentText" ).write( TestConstants.TESTTEXT );
            clickOn( "#uploadImageBtn" ).press( KeyCode.RIGHT ).press( KeyCode.ENTER );
        } );

        step( "Complete post", () -> {
            clickOn( "#submitBtn" );
         //   WaitForAsyncUtils.sleep( 2, TimeUnit.SECONDS );
            verifyThat( lookup( "#username" ).nth( 0 ), hasText(TestConstants.USERNAME ) );
            verifyThat( lookup( "#content" ).nth( 0 ), hasText( TestConstants.TESTTEXT) );
            verifyThat( lookup( "#updateTime" ).nth( 0 ), hasText( "Just now" ) );
            assertNotNull(lookup( "#imageBox" ).nth( 0 ));
            clickOn( "#closeBtn" );
        } );
    }

    @Test
    public void show_3_post_as_default() {
        verifyThat( lookup( "#postBox" ).nth( 0 ), isVisible() );
        verifyThat( lookup( "#postBox" ).nth( 1 ), isVisible() );
        verifyThat( lookup( "#postBox" ).nth( 2 ), isVisible() );
    }


    private void step(final String step, final Runnable runnable) {
        ++stepNo;
        LOG.info( "STEP {}: Begin - {}", stepNo, step );
        runnable.run();
        LOG.info( "STEP {}:   End - {}", stepNo, step );
    }
}
