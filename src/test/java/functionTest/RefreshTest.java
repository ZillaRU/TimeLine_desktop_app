package functionTest;

import home.Main;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class RefreshTest extends ApplicationTest {
    private static final Logger LOG = LoggerFactory.getLogger(RefreshTest.class);
    Stage stage;
    Application main;
    private int stepNo;

    @Before
    public void setup() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        main = FxToolkit.setupApplication(Main.class);
    }

    @After
    public void cleanup() throws Exception {
        FxToolkit.cleanupStages();
        FxToolkit.cleanupApplication(main);
    }

    @Test
    public void refresh_the_list() {
        step("login", () -> {
            clickOn("#nameField").write(TestConstants.ANOTHER_USERNAME);
            clickOn("#passwordField").write(TestConstants.ANOTHER_PASSWORD);
            clickOn("#signInButton");
            clickOn("#closeBtn");
            verifyThat("#refreshBtn", isVisible());
        });

//        step("other post news", () -> {
//            PostTest posttest =new PostTest();
//            try {
//               // WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
//                posttest.setup();
//                posttest.post_with_no_image_and_comments_and_show_just_now();
//                WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
//                push(KeyCode.COMMAND,KeyCode.Q);
//                WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//        step("refresh", () -> {
//           // WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
//
//            clickOn("#refreshBtn");
//
//
//        });
    }

    private void step(final String step, final Runnable runnable) {
        ++stepNo;
        LOG.info("STEP {}: Begin - {}", stepNo, step);
        runnable.run();
        LOG.info("STEP {}:   End - {}", stepNo, step);
    }

}
