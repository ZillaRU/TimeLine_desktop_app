package home;

import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertFalse;

//import org.junit.Test;

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