package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import db.PostDAO;
import home.ConstantSetting;
import home.Main;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import model.Post;
import utils.AlertHelper;
import utils.IdGenerator;

import java.io.File;
import java.util.List;

/**
 * @author: zilla0148
 * @date: 2018/12/20 18:15
 */
public class NewPostController {
    private final FileChooser fileChooser = new FileChooser();
    @FXML
    public JFXButton submitBtn;
    @FXML
    public JFXTextArea contentText;
    @FXML
    public JFXButton uploadImageBtn;
    private List<File> imageFileList = null;

    public void handleSubmitAction() {
        Post newPost = new Post( IdGenerator.getId(), Main.getApp().getCurrentUser().getUserID(),
                null, imageFileList != null, contentText.getText() );
        if (new PostDAO().addPost( newPost, imageFileList )) {
            AlertHelper.showJDialog( Main.getApp().getStage(),
                    ConstantSetting.ALERT_TITLE,
                    "Post successfully~" );
            PostsController.NEW_POST_STAGE.close();
            PostsController.getPostsController().refresh();
        }
    }


    public void handleUploadImageAction() {
        fileChooser.setTitle( "Open Image File" );
        fileChooser.setSelectedExtensionFilter( new FileChooser.ExtensionFilter( "image", "jpg", "png", "jpeg", "bmp" ) );
        imageFileList = fileChooser.showOpenMultipleDialog( PostsController.getNewPostStage() );
    }
}
