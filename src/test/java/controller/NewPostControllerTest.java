package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import home.Main;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NewPostControllerTest extends ApplicationTest{
    Stage stage;
    private NewPostController newPostController=new NewPostController();

    @Before
    public void setup() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication( Main.class );
        newPostController.contentText=new JFXTextArea("aaaaaaaaaa");
        newPostController.imageFileList=new ArrayList<File>();
        newPostController.submitBtn=new JFXButton();
        newPostController.uploadImageBtn=new JFXButton();
        newPostController.fileChooser=new FileChooser();

    }

    @Test
    public void handle_Upload_Image_successfully() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String alert=newPostController.handleUploadImageAction();
                assertEquals("Load Successfully~",alert);
            }});
    }
}