package ui;

import com.jfoenix.controls.JFXListCell;
import home.ConstantSetting;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Post;
import utils.TimeDisplay;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zilla0148
 * @date: 2018/12/20 23:34
 */
public class PostCell extends JFXListCell<Post> {
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
        fxmlLoader = new FXMLLoader( getClass().getResource(
                "/fxml/post_cell.fxml" ) );
        fxmlLoader.setRoot( this );
        fxmlLoader.setController( this );
        try {
            setGraphic( fxmlLoader.load() );
        } catch (IOException exception) {
            throw new RuntimeException( exception );
        }
    }


    public void setPostContent(Post item) {
        username.setText( item.getUserID() );
        updateTime.setText( TimeDisplay.format( item.getTimeStamp() ) );
        content.setText( item.getContent() );
        if (item.isWithImgs()) {
            List<String> urls = new ArrayList<>( item.getImgFiles() );
            for (String imageUrl : urls) {
                String fileUrl = ConstantSetting.POST_IMAGE_PATH + imageUrl;
                BufferedImage bufferedImage;
                try {
                    bufferedImage = ImageIO.read( new File( fileUrl ) );
                    Image image = SwingFXUtils.toFXImage( bufferedImage, null );
                    ImageView imageView = new ImageView( image );
                    imageView.setFitHeight( 80 );
                    imageView.setFitWidth( 70 );
                    imageHBox.getChildren().add( imageView );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public VBox getBox() {
        return postBox;
    }
}
