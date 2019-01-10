package controller;

import com.jfoenix.controls.JFXListView;
import home.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.ModelConstants;
import model.Post;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class PostCellFactoryTest extends ApplicationTest{
    Stage stage;
    //PostCell postCell=Mockito.mock(PostCell.class);

    @Before
    public void setup() throws Exception {
        stage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication( Main.class );
    }

    @Test
    public void add_when_update_item_return_one_more_post(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                JFXListView<Post> postListView= new JFXListView<>();
                ObservableList<Post> postDataList;
                postDataList=FXCollections.observableArrayList();
                postListView.setId( "postListView" );
                postListView.getStyleClass().add( "table-view" );
                postListView.setItems(postDataList);
                postListView.setCellFactory( param -> {
                    return new PostCellFactory();
                });

                Post post=new Post(ModelConstants.postID,ModelConstants.userID,
                        ModelConstants.timeStamp,ModelConstants.withImgs,ModelConstants.content);
                ModelConstants.setImgFiles();
                assertEquals(0,postDataList.size());
                postDataList.add(post);
                assertEquals(1,postDataList.size());

            }});

    }

}