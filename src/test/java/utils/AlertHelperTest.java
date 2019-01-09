package utils;

import home.Main;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;


public class AlertHelperTest extends ApplicationTest {

    private Stage stage;

    @Before
    public void setUp() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    @Test
    public void show_JDialog_show() {

        String head = "prompt";
        String body = "Welcome";
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AlertHelper.showJDialog(stage, head, body);
                verifyThat("#headLabel", hasText("prompt"));
                verifyThat("#contentLabel", hasText("Welcome"));
                verifyThat("#closeBtn", hasText("Close"));
            }
        });
    }

}
