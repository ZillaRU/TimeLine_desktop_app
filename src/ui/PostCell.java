package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Post;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zilla0148
 * @date: 2018/12/20 23:34
 */
public class PostCell extends ListCell<Post> {
    @FXML
    VBox postBox;
    @FXML
    Label username;
    @FXML
    Label updateTime;
    @FXML
    Label content;
    @FXML
    HBox imageHBox;

    FXMLLoader fxmlLoader;

    public PostCell() {
        System.out.println("PostCell PostCell");
        fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/post_cell.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            setGraphic(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    public void setPostContent(Post item)
    {
        username.setText(item.getUserID());
        updateTime.setText( item.getTimeStamp().toString() );
        content.setText(item.getContent());
        if (item.isWithImgs()) {
            try {
                List<String> urls=new ArrayList<>( item.getImgFiles() );
                for (String imageUrl:urls) {
                    String fileUrl="file://"+System.getProperty("user.dir")+"/post_images/" + imageUrl;
                    System.out.println( fileUrl );
                    ImageView imageView = new ImageView(fileUrl);
                    imageHBox.getChildren().add(imageView);
                }
                setGraphic(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public VBox getBox()
    {
        return postBox;
    }
}
