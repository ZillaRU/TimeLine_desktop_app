package model;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: zilla0148
 * @date: 2018/12/17 14:41
 */
public class Post {
    private String postID;
    private String userID;
    private Timestamp timeStamp;
    private boolean withImgs;
    private String content;

    private List<String> imgFiles;

    public Post(String postID, String userID, Timestamp timeStamp, boolean withImgs, String content) {
        this.postID = postID;
        this.userID = userID;
        this.timeStamp = timeStamp;
        this.withImgs = withImgs;
        this.content = content;
    }

    public String getPostID() {
        return postID;
    }

    public String getUserID() {
        return userID;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public boolean isWithImgs() {
        return withImgs;
    }

    public String getContent() {
        return content;
    }

    public List<String> getImgFiles() {
        return imgFiles;
    }

    public void setImgFiles(List<String> imgFiles) {
        this.imgFiles = imgFiles;
    }

}
