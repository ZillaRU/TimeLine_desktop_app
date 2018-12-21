package controller;

import db.DBInterface;
import home.Main;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Post;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author: zilla0148
 * @date: 2018/12/17 16:04
 */
public class PostsController implements Initializable {
    @FXML public Button newPostBtn;
    @FXML public Button refreshBtn;

    @FXML public ListView<Post> postListView;
    private ObservableList<Post> postDataList=new ObservableList<Post>() {
        @Override
        public void addListener(ListChangeListener<? super Post> listener) {

        }

        @Override
        public void removeListener(ListChangeListener<? super Post> listener) {

        }

        @Override
        public boolean addAll(Post... elements) {
            return false;
        }

        @Override
        public boolean setAll(Post... elements) {
            return false;
        }

        @Override
        public boolean setAll(Collection<? extends Post> col) {
            return false;
        }

        @Override
        public boolean removeAll(Post... elements) {
            return false;
        }

        @Override
        public boolean retainAll(Post... elements) {
            return false;
        }

        @Override
        public void remove(int from, int to) {

        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Post> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Post post) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Post> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Post> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Post get(int index) {
            return null;
        }

        @Override
        public Post set(int index, Post element) {
            return null;
        }

        @Override
        public void add(int index, Post element) {

        }

        @Override
        public Post remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Post> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Post> listIterator(int index) {
            return null;
        }

        @Override
        public List<Post> subList(int fromIndex, int toIndex) {
            return null;
        }

        @Override
        public void addListener(InvalidationListener listener) {

        }

        @Override
        public void removeListener(InvalidationListener listener) {

        }
    };

    private Stage newPostStage;
    private Main app=Main.getApp();;

    public boolean getPostsData() throws SQLException {
        ResultSet resultSet=DBInterface.getResultSet("SELECT * FROM post ORDER BY updateTime desc;");
        if (resultSet != null) {
            while (resultSet.next()){
                try {
                    Integer postID=resultSet.getInt( "postID");
                    boolean hasImg=resultSet.getBoolean( "hasImg" );
                    Post post=new Post(
                            postID,
                            resultSet.getString( "userID" ),
                            resultSet.getTimestamp( "updateTime" ),
                            hasImg,
                            resultSet.getString( "content" )
                            );
                    if(hasImg){
                        List<String> imageUrlList = new ArrayList<>();
                        ResultSet images=DBInterface.getResultSet( "SELECT imgUrl FROM post_img "
                                + "WHERE postID = '"
                                + postID+"';");
                        while (images.next()){
                            imageUrlList.add( images.getString( "imgUrl" ) );
                        }
                        post.setImgFiles( imageUrlList );
                    }
                    postDataList.add(post);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleNewPostBtnAction(ActionEvent event) throws IOException {
        app.getStage().setOnCloseRequest( event1 -> {
            this.newPostStage.close();
        } );
        Parent target = FXMLLoader.load(getClass().getResource("/fxml/new_post.fxml"));
        Scene scene = new Scene(target);
        newPostStage=new Stage();
        newPostStage.setScene(scene);
        newPostStage.show();
    }

    public void handleRefreshBtnAction(ActionEvent event) {
        try {
            getPostsData();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
