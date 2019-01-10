package db;

import home.ConstantSetting;
import model.ModelConstants;
import model.Post;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PostDAOTest {
    @Before
    public void setUp() throws Exception {
        if(DBConnector.getInstance().getConnection()==null||DBConnector.getInstance().getConnection().isClosed()){
            DBConnector.getInstance().createConnection( ConstantSetting.DB_URL,
                    ConstantSetting.DB_USER,
                    ConstantSetting.DB_PASSWORD,
                    ConstantSetting.DB_NAME);
        }
    }

    @Test
    public void add_post_with_no_image(){
        Post post=new Post(ModelConstants.postID,ModelConstants.userID,
                ModelConstants.timeStamp,ModelConstants.withNoImg,ModelConstants.content);
        PostDAO postDAO=new PostDAO();
        List<File> imageFileList = null;
        boolean success=postDAO.addPost(post,imageFileList);
        assertTrue(success);
    }
    @Test
    public void add_post_with_images(){
        ModelConstants.setId();
        Post post=new Post(ModelConstants.postID,ModelConstants.userID,
                ModelConstants.timeStamp,ModelConstants.withImgs,ModelConstants.content);
        ModelConstants.setImgFiles();
        List<File> files=new ArrayList<>();
        files.add(new File(ConstantSetting.POST_IMAGE_PATH + ModelConstants.IMG1));
        files.add(new File(ConstantSetting.POST_IMAGE_PATH + ModelConstants.IMG2));
        PostDAO postDAO=new PostDAO();
        boolean success=postDAO.addPost(post,files);
        assertTrue(success);
    }

    @Test
    public void get_post_with_1000_page_return_0_post(){
        PostDAO postDAO=new PostDAO();
        List<Post> resultList=postDAO.getPosts(1000);
        assertEquals(0,resultList.size());
    }

    @Test
    public void get_post_with_first_page_return_3_post(){
        PostDAO postDAO=new PostDAO();
        List<Post> resultList=new ArrayList<>();
        assertEquals(0,resultList.size());
        resultList=postDAO.getPosts(1);
        assertEquals(3,resultList.size());
    }

    @Test
    public void countUpdate() {
        PostDAO postDAO=new PostDAO();
        int updateNum= postDAO.countUpdate(new Timestamp(System.currentTimeMillis()-24 * 60 * 60 * 1000L));
        assertTrue(updateNum>0);
    }
}