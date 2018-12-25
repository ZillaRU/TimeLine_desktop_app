package db;

import home.ConstantSetting;
import model.Post;
import utils.IdGenerator;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zilla0148
 * @date: 2018/12/25 14:23
 */
public class PostDAO {
    public boolean addPost(Post newPost, List<File> imageFileList){
        String stmt="INSERT INTO post(postID, username, hasImg, content) "
                + "VALUES ('"
                + newPost.getPostID() + "', '"
                + newPost.getUserID() + "', "
                + newPost.isWithImgs() + ", '"
                + newPost.getContent() + "');";
        try {
            if (DBInterface.executeStatement( stmt )) {
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
                                + "VALUES ('"
                                + newPost.getPostID() + "', '"
                                + newImageName + "');";
                        DBInterface.executeStatement( stmt1 );
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

    public boolean deletePost(){
        return true;
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
