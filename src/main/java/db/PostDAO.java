package db;

import home.ConstantSetting;
import model.Post;
import utils.IdGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zilla0148
 * @date: 2018/12/25 14:23
 */
public class PostDAO {
    private Connection con = DBConnector.getInstance().getConnection();
    public boolean addPost(Post newPost, List<File> imageFileList){
        String stmt="INSERT INTO post(postID, username, hasImg, content) "
                + "VALUES (?,?,?,?);";

        try {
            PreparedStatement statement=con.prepareStatement( stmt );
            statement.setString( 1, newPost.getPostID());
            statement.setString( 2,newPost.getUserID() );
            statement.setBoolean( 3 ,newPost.isWithImgs());
            statement.setString( 4,newPost.getContent() );
            System.out.println( statement );
            if (statement.executeUpdate()==1) {
                if (newPost.isWithImgs()) {
                    FileInputStream inputStream;
                    FileOutputStream outputStream;
                    for (File file : imageFileList) {
                        String originImageName = file.getName();
                        String newImageName = IdGenerator.getId() + "."
                                + originImageName.substring( originImageName.lastIndexOf( "." ) + 1 );
                        inputStream = new FileInputStream( file );
                        outputStream = new FileOutputStream( ConstantSetting.POST_IMAGE_PATH
                                + "/" + newImageName );
                        byte[] buffer = new byte[1024];
                        int cnt = 0;
                        while ((cnt = inputStream.read( buffer )) > 0) {
                            outputStream.write( buffer, 0, cnt );
                        }
                        String stmt1 = "INSERT INTO post_img(postID, imgUrl) "
                                + "VALUES (?,?)";
                        PreparedStatement statement1=con.prepareStatement( stmt1 );
                        statement.setString( 1, newPost.getPostID());
                        statement.setString( 2,newImageName );
                        statement1.executeUpdate();
                        outputStream.close();
                        inputStream.close();
                    }
                }
                return true;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public List<Post> getPosts(int page){
        List<Post> resultList=new ArrayList<>();
        ResultSet resultSet = DBInterface.getResultSet("SELECT * FROM post ORDER BY updateTime desc "
                +"LIMIT "+page*ConstantSetting.POST_NUM_PAGE+","+ConstantSetting.POST_NUM_PAGE+";");
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    String postID = resultSet.getString( "postID" );
                    boolean hasImg = resultSet.getBoolean( "hasImg" );
                    Post post = new Post(
                            postID,
                            resultSet.getString( "username" ),
                            resultSet.getTimestamp( "updateTime" ),
                            hasImg,
                            resultSet.getString( "content" )
                    );
                    if (hasImg) {
                        List<String> imageUrlList = new ArrayList<>();
                        ResultSet images = DBInterface.getResultSet( "SELECT imgUrl FROM post_img "
                                + "WHERE postID = '"
                                + postID + "';" );
                        if (images != null) {
                            while (images.next()) {
                                imageUrlList.add( images.getString( "imgUrl" ) );
                            }
                        }
                        post.setImgFiles( imageUrlList );
                    }
                    resultList.add( post );
                }
                return resultList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public int countUpdate(Timestamp timestamp){
        String stmt = "SELECT count(*) as updateCnt FROM post WHERE updateTime > '"+
                timestamp.toString()+
                "' ORDER BY updateTime DESC;";
        System.out.println( stmt );
        try {
            ResultSet resultSet=DBInterface.getResultSet( stmt );
            if(resultSet!=null){
                resultSet.next();
                System.out.println( "countUpdate"+resultSet.getInt( "updateCnt" ));
                return resultSet.getInt( "updateCnt" );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
